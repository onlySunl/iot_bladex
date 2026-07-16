package org.springblade.modules.iot.framework.tenant.core.aop;

import java.lang.annotation.*;

/**
 * TenantIgnore annotation - marks a method to skip tenant filtering.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantIgnore {
}
