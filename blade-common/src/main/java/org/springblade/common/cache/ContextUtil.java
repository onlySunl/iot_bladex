package org.springblade.common.cache;

import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;

/**
 * 上下文工具类
 */
public class ContextUtil {
    
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();
    
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }
    
    public static String getTenantId() {
        return TENANT_ID.get();
    }
    
    public static void setUserId(String userId) {
        USER_ID.set(userId);
    }
    
    public static String getUserId() {
        return USER_ID.get();
    }
    
    public static void clear() {
        TENANT_ID.remove();
        USER_ID.remove();
    }
}
