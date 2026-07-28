package org.springblade.common.iot.cache.tenant.tenant;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.basic.utils.StrPool;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 租户 KEY
 * [服务模块名:]业务类型[:业务字段][:value类型][:租户id] -> obj
 * tenant:def_tenant:id:obj:1 -> {}
 *
 * <p>
 * #def_tenant
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
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
        return SuperEntity.ID_FIELD;
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
