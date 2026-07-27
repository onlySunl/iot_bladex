package org.springblade.common.cache.tenant.tenant;

import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.utils.StrPool;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * ???????KEY
 * [???????????????]???????????????:???????????????[:value????????:????????] -> obj
 * tenant:def_tenant:id:obj:1 -> {}
 *
 * <p>
 * #def_tenant
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 ???????
 */
public class TenantCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(Long id) {
        return new TenantCacheKeyBuilder().key(id);
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
        return CacheKeyTable.System.TENANT;
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
