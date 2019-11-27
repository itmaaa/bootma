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
     * 锁存放的目录
     */
    String catalog() default "";

    /**
     * 锁超时释放时间,默认-1L，加锁默认30s,每隔10s自动续期
     */
    long leaseTime() default -1L;

    /**
     * 等待加锁超时时间
     * 默认-1L 则表示使用lock 一直等待加锁,有值使用tryLock尝试加锁，超过时间放弃
     */
    long waitTime() default -1L;


}
