package org.springblade.common.cache;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 缓存哈希键
 */
@Data
@NoArgsConstructor
public class CacheHashKey {
    private String key;
    private String hashKey;
    
    public CacheHashKey(String key, String hashKey) {
        this.key = key;
        this.hashKey = hashKey;
    }
    
    public static CacheHashKey of(String key, String hashKey) {
        return new CacheHashKey(key, hashKey);
    }
}
