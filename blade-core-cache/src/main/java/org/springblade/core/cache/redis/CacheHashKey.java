package org.springblade.core.cache.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 缓存 Hash Key
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheHashKey {

    /**
     * 缓存键
     */
    private String key;

    /**
     * Hash 字段
     */
    private String field;

    public static CacheHashKey of(String key, String field) {
        return new CacheHashKey(key, field);
    }
}
