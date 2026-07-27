package org.springblade.common.cache.tenant.application;

import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 资源 KEY
 * [服务模块名:]业务类型[:业务字段][:value类型][:资源id] -> obj
 * tenant:def_resource:id:obj:1 -> {}
 * <p>
 * #def_resource
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class ResourceCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(Long id) {
        return new ResourceCacheKeyBuilder().key(id);
    }

    @Override
    public String getTenant() {
        return null;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.RESOURCE;
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
