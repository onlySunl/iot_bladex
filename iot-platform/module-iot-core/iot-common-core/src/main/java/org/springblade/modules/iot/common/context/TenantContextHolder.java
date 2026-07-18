package org.springblade.modules.iot.common.context;


import jodd.util.StringUtil;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.util.StringUtils;

/**
 * 多租户上下文 Holder
 *
 * @author EnjoyIot
 */
public class TenantContextHolder {


    /**
     * 获得租户编号
     *
     * @return 租户编号
     */
    public static Long getTenantId() {
        return Func.toLong(AuthUtil.getTenantId());
    }

    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    public static Long getRequiredTenantId() {
        Long tenantId = getTenantId();
        if (StringUtil.isBlank(Func.toStr(tenantId))) {
            throw new NullPointerException("TenantContextHolder 不存在租户编号！");
        }
        return Func.toLong(tenantId);
    }

    public static void setTenantId(Long tenantId) {
        //TENANT_ID.set(tenantId);
    }

    public static void setIgnore(Boolean ignore) {
        //IGNORE.set(ignore);
    }

    /**
     * 当前是否忽略租户
     *
     * @return 是否忽略
     */
    public static boolean isIgnore() {
       // return Boolean.TRUE.equals(IGNORE.get());
        return false;
    }

    public static void clear() {
        //TENANT_ID.remove();
        //IGNORE.remove();
    }

}