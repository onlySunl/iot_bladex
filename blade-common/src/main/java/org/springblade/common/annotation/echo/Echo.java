package org.springblade.common.annotation.echo;

import java.lang.annotation.*;

/**
 * 回显注解
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Echo {
    String value() default "";
    String ref() default "";
    String bean() default "";
    String method() default "";
}
