package org.springblade.common.cache.auth;


import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.core.tool.utils.Func;

import java.time.Duration;

/**
 * 员工 是否系统管理员
 * <p>
 * 完整key: ${companyId}:is_sys_admin:${employeeId} -> "1" or "0"
 * <p>
 *
 * @author mqttsnet
 * @date 2021/12/20 6:45 下午
 */
public class IsTenantAdminCacheKeyBuilder implements CacheKeyBuilder {
    private Long tenantId;

    public static CacheKey builder(Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(employeeId);
    }

    public static CacheKey builder(String tenantId, Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().setTenantId(Func.toLong(tenantId)).key(employeeId);
    }


    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public IsTenantAdminCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.COMMON;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.TENANT;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
