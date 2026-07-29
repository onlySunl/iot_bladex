package org.springblade.core.annotation.database;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mqttsnet
 * @version v1.0
 * @date 2022/8/24 9:19 PM
 * @create [2022/8/24 9:19 PM ] [mqttsnet] [初始创建]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TenantLine {
    boolean value() default true;
}
