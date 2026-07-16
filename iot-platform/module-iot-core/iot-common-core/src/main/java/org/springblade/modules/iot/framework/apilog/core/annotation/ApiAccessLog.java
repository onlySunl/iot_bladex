package org.springblade.modules.iot.framework.apilog.core.annotation;

import java.lang.annotation.*;

/**
 * ApiAccessLog annotation - marks a method for API access logging.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiAccessLog {
}
