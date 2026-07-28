package org.springblade.core.cache.lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 锁执行结果
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockRunResult<T> {

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

    public static <T> LockRunResult<T> success(T data) {
        return new LockRunResult<>(true, data, null);
    }

    public static <T> LockRunResult<T> fail(String error) {
        return new LockRunResult<>(false, null, error);
    }
}
