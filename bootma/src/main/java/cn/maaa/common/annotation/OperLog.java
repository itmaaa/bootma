package cn.maaa.common.annotation;

import lombok.experimental.Accessors;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author mazh
 * @date 2019年02月26日 11:03 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperLog {
       String value() default "";
}
