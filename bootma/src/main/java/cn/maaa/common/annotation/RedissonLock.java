package cn.maaa.common.annotation;

import cn.maaa.common.constants.LockMode;

import java.lang.annotation.*;

/**
 * @author :  mazh
 * @date :  2019/11/26 17:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonLock {

    /**
     * 锁的模式:如果不设置,自动模式,当参数只有一个.使用 REDLOCK 参数多个 MULTIPLE
     */
    LockMode lockMode() default LockMode.AUTO;

    /**
     * 锁的key
     */
    String[] keys() default {};

    /**
     * 锁超时释放时间,默认30000毫秒
     */
    long leaseTime() default 30000L;

    /**
     * 等待加锁超时时间,默认10000毫秒 -1 则表示一直等待
     */
    long waitTime() default -1L;


}
