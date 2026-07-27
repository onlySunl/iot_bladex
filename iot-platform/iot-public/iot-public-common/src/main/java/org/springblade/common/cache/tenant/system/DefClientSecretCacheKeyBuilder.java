package org.springblade.common.cache.tenant.system;

import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 客户端
 * <p>
 * #def_client
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DefClientSecretCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(String clientId, String clientSecret) {
        return new DefClientSecretCacheKeyBuilder().key(clientId, clientSecret);
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
        return "cid.secret";
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
