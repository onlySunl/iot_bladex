package org.springblade.modules.iot.cache;

import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springframework.stereotype.Component;

/**
 * 缓存抽象基类
 */
@Component
public abstract class CacheSuperAbstract {
    
    protected String buildKey(String... parts) {
        return String.join(":", parts);
    }
    
    protected CacheKey cacheKey(String key) {
        return CacheKey.of(key);
    }
}
