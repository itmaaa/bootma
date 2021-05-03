package cn.maaa.test.java8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/5/3 9:55
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Hints {
    Hint[] value();
}
@Repeatable(Hints.class)
@interface Hint {
    String value();
}

@Hint("DDD")
@Hint("CCC")
class Person {}


