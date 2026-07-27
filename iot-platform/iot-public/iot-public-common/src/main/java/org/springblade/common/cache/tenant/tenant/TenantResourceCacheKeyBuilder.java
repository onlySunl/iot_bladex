package org.springblade.common.cache.tenant.tenant;

import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.utils.StrPool;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 绉熸埛鎷ユ湁鐨勮祫婧?
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 涓嬪崍
 */
public class TenantResourceCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(String tenantId, Long applicationId) {
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
        return CustomBaseEntity.ID_FIELD;
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
