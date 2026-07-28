package org.springblade.core.annotation.echo;

import java.lang.annotation.*;

/**
 * 数据回显注解
 *
 * @author Chill
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Echo {

    /**
     * 回显类型
     */
    String type() default "";

    /**
     * 映射字段
     */
    String ref() default "";

    /**
     * 是否忽略
     */
    boolean ignore() default false;
}
