package cn.maaa.common.annotation;

import java.lang.annotation.*;

/**
 * bean复制注解
 * @author mazh
 * @date 2019年02月28日 11:07 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Convert {
    String value() default "";
}
