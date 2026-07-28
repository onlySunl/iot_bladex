package org.springblade.core.cache.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 缓存结果
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheResult<T> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 结果数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String error;

    public static <T> CacheResult<T> success(T data) {
        return new CacheResult<>(true, data, null);
    }

    public static <T> CacheResult<T> fail(String error) {
        return new CacheResult<>(false, null, error);
    }
}
