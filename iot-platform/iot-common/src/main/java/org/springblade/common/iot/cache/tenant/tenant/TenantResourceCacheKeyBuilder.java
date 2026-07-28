package org.springblade.common.iot.cache.tenant.tenant;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.basic.utils.StrPool;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 租户拥有的资源
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class TenantResourceCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(Long tenantId, Long applicationId) {
        return new TenantResourceCacheKeyBuilder().key(tenantId, applicationId);
    }

    @Override
    public String getTenant() {
        return StrPool.EMPTY;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.TENANT_APPLICATION_RESOURCE;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.number;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
