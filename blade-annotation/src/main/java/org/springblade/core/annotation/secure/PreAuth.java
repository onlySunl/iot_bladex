package org.springblade.core.annotation.secure;

import java.lang.annotation.*;

/**
 * 权限预认证注解
 *
 * @author Chill
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuth {

    /**
     * 权限表达式
     */
    String value() default "";

    /**
     * 是否替换前缀
     */
    boolean replace() default true;
}
