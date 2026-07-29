package org.springblade.core.cache.properties;

/**
 * 缓存类型
 *
 * @author mqttsnet
 * @date 2023/9/22 3:34 下午
 */
public enum CacheType {
    /**
     * 内存
     */
    CAFFEINE,
    /**
     * redis
     */
    REDIS;

    public boolean eq(CacheType cacheType) {
        return cacheType != null && this.name().equals(cacheType.name());
    }
}
