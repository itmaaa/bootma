package cn.maaa.common.aspect;

/**
 * @author :  mazh
 * @date :  2019/11/26 17:41
 */

import cn.maaa.common.annotation.RedissonLock;
import cn.maaa.common.constants.LockMode;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁切面
 */
@Aspect
@Slf4j
public class LockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(redissonLock)")
    public void lockPointCut(RedissonLock redissonLock) {
    }

    /**
     * 通过spring Spel 获取参数
     *
     * @param key            定义的key值 以#开头 例如:#user
     * @param parameterNames 形参
     * @param values         形参值
     * @return
     */
    public String parseKey(String key, String[] parameterNames, Object[] values) {

        if (!key.contains("#")) {
            return "redisson:lock:" + key;
        }
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        Expression expression = parser.parseExpression(key);
        Object value = expression.getValue(context);
        if(value == null)
            throw  new RuntimeException("spel表达式解析错误");
        return "redisson:lock:" + value.toString();
    }

    @Around("lockPointCut(redissonLock)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, RedissonLock redissonLock) throws Throwable {
        String[] keys = redissonLock.keys();
        if (keys.length == 0) {
            throw new RuntimeException("keys不能为空");
        }
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) proceedingJoinPoint.getSignature()).getMethod());
        Object[] args = proceedingJoinPoint.getArgs();

        LockMode lockMode = redissonLock.lockMode();
        if (lockMode.equals(LockMode.AUTO)) {
            // 大部分节点上加锁成功  -> 成功
            lockMode = LockMode.REDLOCK;
            // 所有的锁都加锁成功 -> 成功
            if (keys.length > 1) {
                lockMode = LockMode.MULTIPLE;
            }
            if (!lockMode.equals(LockMode.MULTIPLE) && !lockMode.equals(LockMode.REDLOCK) && keys.length > 1) {
                throw new RuntimeException("参数有多个,锁模式为->" + lockMode.name() + "无法锁定");
            }
            long waitTime = redissonLock.waitTime();
            long leaseTime = redissonLock.leaseTime();
            boolean res = false;
            RLock lock = null;
            //一直等待加锁.
            switch (lockMode) {
                case FAIR:
                    lock = redissonClient.getFairLock(parseKey(keys[0], parameterNames, args));
                    break;
                case REDLOCK:
                    RLock[] locks = new RLock[keys.length];
                    int index = 0;
                    for (String key : keys) {
                        locks[index++] = redissonClient.getLock(parseKey(key, parameterNames, args));
                    }
                    lock = new RedissonRedLock(locks);
                    break;
                case MULTIPLE:
                    RLock[] locks1 = new RLock[keys.length];
                    int index1 = 0;
                    for (String key : keys) {
                        locks1[index1++] = redissonClient.getLock(parseKey(key, parameterNames, args));
                    }
                    lock = new RedissonMultiLock(locks1);
                    break;
                case REENTRANT:
                    lock = redissonClient.getLock(parseKey(keys[0], parameterNames, args));
                    break;
                case READ:
                    RReadWriteLock rwlock = redissonClient.getReadWriteLock(parseKey(keys[0], parameterNames, args));
                    lock = rwlock.readLock();
                    break;
                case WRITE:
                    RReadWriteLock rwlock1 = redissonClient.getReadWriteLock(parseKey(keys[0], parameterNames, args));
                    lock = rwlock1.writeLock();
                    break;
                default:
                    break;
            }

            //执行aop
            if (lock != null) {
                try {
                    if (waitTime == -1L) {
                        res = true;
                        //一直等待加锁
                        lock.lock(leaseTime, TimeUnit.MILLISECONDS);

                    } else {
                        res = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
                    }
                    if (res) {
                        log.debug("加锁成功,锁模式->{},等待锁定时间->{}秒.锁失效释放时间->{}秒", lockMode.name(), waitTime, leaseTime);
                        Object obj = proceedingJoinPoint.proceed();
                        return obj;
                    }
                } finally {
                    if (res) {
                        lock.unlock();
                    }
                }
            }
            log.warn("获取锁:{}失败", redissonLock.keys());
        }
        return null;
    }

}