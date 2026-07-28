package org.springblade.core.annotation.log;

import java.lang.annotation.*;

/**
 * Web 日志注解
 *
 * @author Chill
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {

    /**
     * 日志描述
     */
    String value() default "";

    /**
     * 是否记录请求参数
     */
    boolean request() default true;

    /**
     * 是否记录响应结果
     */
    boolean response() default false;
}
