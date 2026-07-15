package org.springblade.modules.iot.framework.security.core.util;

import org.springblade.core.secure.utils.AuthUtil;

/**
 * SecurityFrameworkUtils adapter - delegates to BladeX AuthUtil.
 */
public class SecurityFrameworkUtils {
    public static Long getLoginUserId() {
        try {
            return AuthUtil.getUserId();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTenantId() {
        try {
            return AuthUtil.getTenantId();
        } catch (Exception e) {
            return null;
        }
    }
}
