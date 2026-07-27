package org.springblade.common.cache;

/**
 * 缓存键构建器
 */
public class CacheKeyBuilder {
    
    private static final String PREFIX = "iot:";
    
    public static CacheKey deviceKey(String deviceId) {
        return CacheKey.of(PREFIX + "device:" + deviceId);
    }
    
    public static CacheKey productKey(String productId) {
        return CacheKey.of(PREFIX + "product:" + productId);
    }
    
    public static CacheKey sessionKey(String sessionId) {
        return CacheKey.of(PREFIX + "session:" + sessionId);
    }
    
    public static CacheKey userKey(String userId) {
        return CacheKey.of(PREFIX + "user:" + userId);
    }
    
    public static CacheKey of(String key) {
        return CacheKey.of(key);
    }
}
