package org.springblade.common.cache.base.system;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * 角色的资源 KEY
 * <p>
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class RoleResourceCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(Long applicationId, Long roleId) {
        return new RoleResourceCacheKeyBuilder().key(applicationId, roleId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.ROLE_RESOURCE;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.BASE;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.number;
    }
}
