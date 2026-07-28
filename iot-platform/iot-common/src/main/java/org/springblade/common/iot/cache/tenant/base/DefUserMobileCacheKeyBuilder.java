package org.springblade.common.iot.cache.tenant.base;

import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #def_user
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DefUserMobileCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(String mobile) {
        return new DefUserMobileCacheKeyBuilder().key(mobile);
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
        return "mobile";
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
