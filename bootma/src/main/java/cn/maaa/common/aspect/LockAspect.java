package cn.maaa.common.aspect;

/**
 * @author :  mazh
 * @date :  2019/11/26 17:41
 */

import cn.maaa.common.annotation.RedissonLock;
import cn.maaa.common.constants.LockMode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static cn.maaa.common.constants.LockMode.REDLOCK;

/**
 * 分布式锁切面
 */
@Aspect
@Component
@Slf4j
public class LockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(redissonLock)")
    public void lockPointCut(RedissonLock redissonLock) {
    }

    @Around("lockPointCut(redissonLock)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, RedissonLock redissonLock) throws Throwable {
        String[] keys = redissonLock.keys();
        if (keys.length == 0) {
            throw new RuntimeException("keys不能为空");
        }
        LockMode lockMode = redissonLock.lockMode();
        if (lockMode.equals(LockMode.AUTO)) {
            // 大部分节点上加锁成功  -> 成功
            lockMode = REDLOCK;
            // 所有的锁都加锁成功 -> 成功
            if (keys.length > 1) {
                lockMode = LockMode.MULTIPLE;
            }
        }
        if (!lockMode.equals(LockMode.MULTIPLE) && !lockMode.equals(REDLOCK) && keys.length > 1) {
            throw new RuntimeException("参数有多个,锁模式为->" + lockMode.name() + "无法锁定");
        }

        RLock lock = null;
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        Object[] args = proceedingJoinPoint.getArgs();

        String catalog = redissonLock.catalog();
        //默认方法名做二级目录
        if(StringUtils.isEmpty(catalog))
            catalog =  method.getName();
        switch (lockMode) {
                case FAIR:
                    lock = redissonClient.getFairLock(parseKey(catalog,keys[0], parameterNames, args));
                    break;
                case REDLOCK:
                case MULTIPLE:
                    RLock[] locks = new RLock[keys.length];
                    int index = 0;
                    for (String key : keys) {
                        locks[index++] = redissonClient.getLock(parseKey(catalog,key, parameterNames, args));
                    }
                    if(lockMode.equals(REDLOCK)){
                        lock = new RedissonRedLock(locks);
                        break;
                    }
                    lock = new RedissonMultiLock(locks);
                    break;
                case REENTRANT:
                    lock = redissonClient.getLock(parseKey(catalog,keys[0], parameterNames, args));
                    break;
                case READ:
                    RReadWriteLock rwlock = redissonClient.getReadWriteLock(parseKey(catalog,keys[0], parameterNames, args));
                    lock = rwlock.readLock();
                    break;
                case WRITE:
                    RReadWriteLock rwlock1 = redissonClient.getReadWriteLock(parseKey(catalog,keys[0], parameterNames, args));
                    lock = rwlock1.writeLock();
                    break;
                default:
                    break;
            }

            //执行aop
            boolean res = false;
            long waitTime = redissonLock.waitTime();
            long leaseTime = redissonLock.leaseTime();
            if (lock != null) {
                try {
                    if (waitTime == -1L) {
                        //一直等待加锁
                        lock.lock(leaseTime, TimeUnit.MILLISECONDS);
                        res = true;
                    } else {
                        res = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
                    }
                    if (res) {
                        log.debug("加锁成功,锁模式->{},锁过期时间->{}秒", lockMode.name(), leaseTime == -1L?leaseTime:leaseTime/1000);
                        Object obj = proceedingJoinPoint.proceed();
                        return obj;
                    }
                } finally {
                    if (res) {
                        lock.unlock();
                    }
                }
            }
            log.debug("加锁失败,锁模式->{},等待加锁时间->{}秒,锁过期时间->{}秒", lockMode.name(), waitTime/1000,leaseTime == -1L?leaseTime:leaseTime/1000);
            return null;
    }


    /**
     * 通过spring Spel 获取参数
     * @param catalog        key存放目录
     * @param key            定义的key值 以#开头 例如:#user
     * @param parameterNames 形参
     * @param values         形参值
     * @return
     */
    public String parseKey(String catalog,String key, String[] parameterNames, Object[] values) {

        if (!key.contains("#")) {
            return StringUtils.joinWith(":","rLock",catalog,key);
        }
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(key);
        Object value = expression.getValue(context);
        if(value == null)
            throw  new RuntimeException("spel表达式解析错误");
        return StringUtils.joinWith(":","rLock",catalog,value.toString());
    }

}