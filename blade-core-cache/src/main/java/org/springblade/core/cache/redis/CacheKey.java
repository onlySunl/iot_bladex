package org.springblade.core.cache.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 缓存 Key
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheKey {

    /**
     * 缓存键
     */
    private String key;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    public CacheKey(String key) {
        this.key = key;
    }

    public CacheKey(String key, Long expire) {
        this.key = key;
        this.expire = expire;
        this.timeUnit = TimeUnit.SECONDS;
    }

    public static CacheKey of(String key) {
        return new CacheKey(key);
    }

    public static CacheKey of(String key, Long expire) {
        return new CacheKey(key, expire);
    }
}
