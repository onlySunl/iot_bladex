package org.springblade.common.iot.cache.tenant.system;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 客户端
 * <p>
 * #def_client
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DefClientCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(Long id) {
        return new DefClientCacheKeyBuilder().key(id);
    }


    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.DEF_CLIENT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
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
