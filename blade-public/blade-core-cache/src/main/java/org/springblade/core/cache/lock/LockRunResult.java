package org.springblade.core.cache.lock;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分布式锁执行结果封装
 * <p>
 * 用于封装有返回值的锁操作结果，包含是否获取锁成功和执行结果
 * </p>
 *
 * @param <R> 返回值类型
 * @author mqttsnet
 * @date 2026-02-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockRunResult<R> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功获取到锁
     * <ul>
     *   <li>true: 成功获取锁并执行了业务逻辑</li>
     *   <li>false: 未获取到锁，业务逻辑未执行</li>
     * </ul>
     */
    private boolean locked;

    /**
     * 业务逻辑执行结果
     * <p>
     * 仅当 {@link #locked} 为 true 时有值，否则为 null
     * </p>
     */
    private R result;

    /**
     * 错误代码
     * <p>
     * 当 {@link #locked} 为 false 时，表示具体的错误类型
     * </p>
     */
    private String errorCode;

    /**
     * 错误信息
     * <p>
     * 当 {@link #locked} 为 false 时，表示具体的错误描述
     * </p>
     */
    private String errorMsg;

    /**
     * 异常信息
     * <p>
     * 当业务逻辑执行过程中发生异常时，记录异常信息
     * </p>
     */
    private Throwable exception;

    /**
     * 构建成功结果
     */
    public static <R> LockRunResult<R> buildSuccess(R result) {
        return new LockRunResult<>(true, result, null, null, null);
    }

    /**
     * 构建获取锁失败的结果
     */
    public static <R> LockRunResult<R> buildGetLockErr() {
        return new LockRunResult<>(false, null, "LOCK_FAILED", "获取锁失败", null);
    }

    /**
     * 构建执行异常的结果
     */
    public static <R> LockRunResult<R> buildError(String errorCode, String errorMsg, Throwable exception) {
        return new LockRunResult<>(false, null, errorCode, errorMsg, exception);
    }

    /**
     * 构建执行异常的结果（简化版）
     */
    public static <R> LockRunResult<R> buildError(Throwable exception) {
        return new LockRunResult<>(false, null, "EXECUTION_ERROR", "业务逻辑执行异常", exception);
    }

    /**
     * 判断是否成功执行
     */
    public boolean isSuccess() {
        return locked && exception == null;
    }

    /**
     * 获取执行结果，如果失败则抛出异常
     */
    public R getResultOrThrow() {
        if (!locked) {
            throw new IllegalStateException("未获取到锁: " + (errorMsg != null ? errorMsg : ""));
        }
        if (exception != null) {
            throw new IllegalStateException("执行异常: " + (errorMsg != null ? errorMsg : ""), exception);
        }
        return result;
    }

    /**
     * 获取执行结果，如果失败则返回默认值
     */
    public R getResultOrDefault(R defaultValue) {
        if (isSuccess()) {
            return result;
        }
        return defaultValue;
    }

    /**
     * 获取错误信息（包含错误代码）
     */
    public String getFullErrorMsg() {
        if (errorCode == null && errorMsg == null) {
            return null;
        }
        if (errorCode == null) {
            return errorMsg;
        }
        if (errorMsg == null) {
            return errorCode;
        }
        return errorCode + ": " + errorMsg;
    }
}
