package cn.maaa.ztest.aop;

import java.lang.annotation.*;

/**
 * AopTest
 * @author mazh
 * @date 2019年03月27日 9:51 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AopTest {
	String value() default "";
}
