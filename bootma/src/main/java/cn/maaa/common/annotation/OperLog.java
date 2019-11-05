package cn.maaa.common.annotation;

import lombok.experimental.Accessors;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author mazh
 * @date 2019年02月26日 11:03 
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperLog {
       String value() default "";
       //是否排除菜单
       boolean exclusive() default false;
       //是否记录日志
       boolean record() default true;
}
