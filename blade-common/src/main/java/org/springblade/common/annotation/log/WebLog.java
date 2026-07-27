package org.springblade.common.annotation.log;

import java.lang.annotation.*;

/**
 * Web 日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    String value() default "";
    String description() default "";
}
