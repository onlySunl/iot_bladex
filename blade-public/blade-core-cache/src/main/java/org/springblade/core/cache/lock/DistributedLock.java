package org.springblade.core.cache.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁顶级接口
 * <p>
 * 提供分布式锁的基础操作，包括传统的锁获取/释放方式和便捷的 tryLockAndRun 方法
 * </p>
 *
 * @author mqttsnet
 * @date 2026-02-26
 */
public interface DistributedLock {

    /**
     * 默认超时时间
     * <p>
     * 单位：毫秒
     * </p>
     */
    long TIMEOUT_MILLIS = 5000;

    /**
     * 默认重试次数
     */
    int RETRY_TIMES = 5;

    /**
     * 默认每次重试后等待的时间
     * <p>
     * 单位：毫秒
     * </p>
     */
    long SLEEP_MILLIS = 500;


    /**
     * 获取锁（使用默认参数）
     *
     * @param key 锁的key
     * @return true-成功获取锁，false-获取锁失败
     */
    default boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    }

    /**
     * 获取锁（指定重试次数）
     *
     * @param key        锁的key
     * @param retryTimes 重试次数
     * @return true-成功获取锁，false-获取锁失败
     */
    default boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
    }

    /**
     * 获取锁（指定重试次数和重试间隔）
     *
     * @param key         锁的key
     * @param retryTimes  重试次数
     * @param sleepMillis 获取锁失败的重试间隔（毫秒）
     * @return true-成功获取锁，false-获取锁失败
     */
    default boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
    }

    /**
     * 获取锁（指定过期时间）
     *
     * @param key    锁的key
     * @param expire 锁的过期时间（毫秒）
     * @return true-成功获取锁，false-获取锁失败
     */
    default boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    }

    /**
     * 获取锁（指定过期时间和重试次数）
     *
     * @param key        锁的key
     * @param expire     锁的过期时间（毫秒）
     * @param retryTimes 重试次数
     * @return true-成功获取锁，false-获取锁失败
     */
    default boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    }

    /**
     * 获取锁（完整参数）
     *
     * @param key         锁的key
     * @param expire      锁的过期时间（毫秒）
     * @param retryTimes  重试次数
     * @param sleepMillis 获取锁失败的重试间隔（毫秒）
     * @return true-成功获取锁，false-获取锁失败
     */
    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * 释放锁
     *
     * @param key 锁的key
     * @return true-成功释放锁，false-释放锁失败
     */
    boolean releaseLock(String key);


    /**
     * 非阻塞获取锁并执行
     * <p>
     * 如果锁已被占用，立即返回 false，不等待
     * </p>
     *
     * @param key      锁的key
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法（无返回值）
     * @return true-获取锁成功并执行完成，false-未获取到锁
     */
    default boolean tryLockAndRun(String key, long expire, TimeUnit timeUnit, Runnable action) {
        return tryLockAndRun(key, 0, expire, timeUnit, action);
    }

    /**
     * 非阻塞获取锁并执行（时间单位为毫秒）
     * <p>
     * 如果锁已被占用，立即返回 false，不等待
     * </p>
     *
     * @param key    锁的key
     * @param expire 锁的最大存活时间（毫秒）
     * @param action 需要执行的方法（无返回值）
     * @return true-获取锁成功并执行完成，false-未获取到锁
     */
    default boolean tryLockAndRun(String key, long expire, Runnable action) {
        return tryLockAndRun(key, 0, expire, TimeUnit.MILLISECONDS, action);
    }

    /**
     * 阻塞获取锁并执行
     * <p>
     * 如果锁已被占用，会等待 waitTime 时间
     * </p>
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法（无返回值）
     * @return true-获取锁成功并执行完成，false-未获取到锁
     */
    boolean tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Runnable action);

    /**
     * 非阻塞获取锁并执行（有返回值）
     * <p>
     * 如果锁已被占用，立即返回，不等待
     * </p>
     *
     * @param key      锁的key
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法
     * @param <R>      方法返回值类型
     * @return 锁执行结果
     */
    default <R> LockRunResult<R> tryLockAndRun(String key, long expire, TimeUnit timeUnit, Supplier<R> action) {
        return tryLockAndRun(key, 0, expire, timeUnit, action);
    }

    /**
     * 非阻塞获取锁并执行（有返回值，时间单位为毫秒）
     * <p>
     * 如果锁已被占用，立即返回，不等待
     * </p>
     *
     * @param key    锁的key
     * @param expire 锁的最大存活时间（毫秒）
     * @param action 需要执行的方法
     * @param <R>    方法返回值类型
     * @return 锁执行结果
     */
    default <R> LockRunResult<R> tryLockAndRun(String key, long expire, Supplier<R> action) {
        return tryLockAndRun(key, 0, expire, TimeUnit.MILLISECONDS, action);
    }

    /**
     * 阻塞获取锁,如果已经被其它任务锁了则等待waitTime
     * <p>
     * 无参有返回值方法，可以使用 Lambda 表达式传参
     * </p>
     * <pre>{@code
     * 示例：
     * LockRunResult<Integer> result = lock.tryLockAndRun("key", 5, 10, TimeUnit.SECONDS, () -> {
     *     return x + y;
     * });
     * }</pre>
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法
     * @param <R>      方法返回值类型
     * @return 锁执行结果
     */
    <R> LockRunResult<R> tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Supplier<R> action);
}
