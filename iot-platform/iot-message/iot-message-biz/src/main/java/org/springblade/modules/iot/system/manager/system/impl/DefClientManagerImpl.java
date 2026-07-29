package org.springblade.modules.iot.system.manager.system.impl;

import com.mqttsnet.basic.base.manager.impl.SuperCacheManagerImpl;
import com.mqttsnet.basic.cache.redis2.CacheResult;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.common.cache.tenant.system.DefClientCacheKeyBuilder;
import org.springblade.modules.iot.common.cache.tenant.system.DefClientSecretCacheKeyBuilder;
import org.springblade.modules.iot.system.entity.system.DefClient;
import org.springblade.modules.iot.system.manager.system.DefClientManager;
import org.springblade.modules.iot.system.mapper.system.DefClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 客户端
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 * @create [2021-10-13] [mqttsnet] 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefClientManagerImpl extends SuperCacheManagerImpl<DefClientMapper, DefClient> implements DefClientManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new DefClientCacheKeyBuilder();
    }

    @Override
    public DefClient getClient(String clientId, String clientSecret) {
        CacheKey key = DefClientSecretCacheKeyBuilder.builder(clientId, clientSecret);
        CacheResult<Long> result = cacheOps.get(key, k -> {
            DefClient one = getOne(Wraps.<DefClient>lbQ().eq(DefClient::getClientId, clientId).eq(DefClient::getClientSecret, clientSecret));
            return one == null ? null : one.getId();
        });
        Long id = result.asLong();
        ArgumentAssert.notNull(id, "客户端[{}]不存在", clientId);
        return getByIdCache(id);
    }
}
