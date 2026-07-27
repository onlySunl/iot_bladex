package org.springblade.common.cache.tenant.base;

import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #def_user
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DefUserCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(Long id) {
        return new DefUserCacheKeyBuilder().key(id);
    }


    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.DEF_USER;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }

}
