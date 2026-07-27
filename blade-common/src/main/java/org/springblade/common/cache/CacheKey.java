package org.springblade.common.cache;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 缓存键
 */
@Data
@NoArgsConstructor
public class CacheKey {
    private String key;
    private String hashKey;
    
    public CacheKey(String key) {
        this.key = key;
    }
    
    public CacheKey(String key, String hashKey) {
        this.key = key;
        this.hashKey = hashKey;
    }
    
    public static CacheKey of(String key) {
        return new CacheKey(key);
    }
    
    public static CacheKey of(String key, String hashKey) {
        return new CacheKey(key, hashKey);
    }
}
