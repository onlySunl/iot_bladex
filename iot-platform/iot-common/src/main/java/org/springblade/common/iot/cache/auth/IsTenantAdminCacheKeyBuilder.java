package org.springblade.common.iot.cache.auth;


import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

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
    private String tenantId;

    public static CacheKey builder(Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(employeeId);
    }

    public static CacheKey builder(String tenantId, Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().setTenantId(tenantId).key(employeeId);
    }


    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public IsTenantAdminCacheKeyBuilder setTenantId(String tenantId) {
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
        return SuperEntity.ID_FIELD;
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
