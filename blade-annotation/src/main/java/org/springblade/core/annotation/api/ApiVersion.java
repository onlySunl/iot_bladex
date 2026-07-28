package org.springblade.core.annotation.api;

import java.lang.annotation.*;

/**
 * API 版本注解
 *
 * @author Chill
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 版本号
     */
    int value();
}
