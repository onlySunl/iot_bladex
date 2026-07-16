package org.springblade.modules.iot.framework.tenant.core.util;

import org.springblade.modules.iot.framework.tenant.core.context.TenantContextHolder;
import java.util.function.Supplier;

/**
 * TenantUtils adapter.
 */
public class TenantUtils {
    public static <T> T execute(String tenantId, Supplier<T> supplier) {
        String oldTenantId = TenantContextHolder.getTenantId();
        try {
            TenantContextHolder.setTenantId(tenantId);
            return supplier.get();
        } finally {
            TenantContextHolder.setTenantId(oldTenantId);
        }
    }
}
