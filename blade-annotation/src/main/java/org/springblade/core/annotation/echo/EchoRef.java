package org.springblade.core.annotation.echo;

import java.lang.annotation.*;

/**
 * 回显引用注解
 *
 * @author Chill
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EchoRef {

    /**
     * 引用字段
     */
    String value() default "";
}
