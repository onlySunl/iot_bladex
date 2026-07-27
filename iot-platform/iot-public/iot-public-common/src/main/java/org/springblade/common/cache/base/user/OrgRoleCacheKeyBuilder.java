package org.springblade.common.cache.base.user;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * 组织的角色
 * <p>
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class OrgRoleCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(Long orgId) {
        return new OrgRoleCacheKeyBuilder().key(orgId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.ORG_ROLE;
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
    public ValueType getValueType() {
        return ValueType.number;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
