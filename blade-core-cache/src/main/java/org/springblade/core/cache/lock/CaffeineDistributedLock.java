package org.springblade.core.cache.lock;

import lombok.extern.slf4j.Slf4j;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * 基于 Caffeine 的分布式锁实现（本地锁）
 * <p>
 * <b>注意</b>：这只是本地锁实现，仅用于防止代码启动报错或单机环境使用。
 * 真正的分布式环境请使用 {@link RedisDistributedLock}。
 * </p>
 * <p>
 * 本实现使用 {@link ReentrantLock} 配合 Caffeine 缓存实现本地锁功能，支持可重入。
 * </p>
 *
 * @author mqttsnet
 * @date 2026-02-26
 */
@Slf4j
public class CaffeineDistributedLock implements DistributedLock {

    /**
     * 本地锁缓存
     * <p>
     * Key: 锁的key<br>
     * Value: 可重入锁
     * </p>
     */
    private final Cache<String, ReentrantLock> lockCache = Caffeine.newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS)
            .maximumSize(10000)
            .build();

    /**
     * 获取锁
     *
     * @param key         锁的key
     * @param expire      锁的过期时间（毫秒）- 注意：Caffeine实现忽略此参数，使用本地锁无过期时间
     * @param retryTimes  重试次数
     * @param sleepMillis 获取锁失败的重试间隔（毫秒）
     * @return true-成功获取锁，false-获取锁失败
     */
    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        ReentrantLock lock = lockCache.get(key, k -> new ReentrantLock());
        try {
            int attempts = 0;
            while (attempts <= retryTimes) {
                if (lock.tryLock()) {
                    if (log.isDebugEnabled()) {
                        log.debug("Caffeine lock acquired, key={}, attempts={}", key, attempts);
                    }
                    return true;
                }
                if (attempts < retryTimes) {
                    Thread.sleep(sleepMillis);
                }
                attempts++;
            }
            if (log.isDebugEnabled()) {
                log.debug("Caffeine lock acquisition failed after {} retries, key={}", retryTimes, key);
            }
            return false;
        } catch (InterruptedException e) {
            log.error("Caffeine lock acquisition interrupted, key:{}", key, e);
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            log.error("Caffeine lock acquisition error, key:{}", key, e);
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param key 锁的key
     * @return true-成功释放锁，false-释放锁失败（锁不存在或非当前线程持有）
     */
    @Override
    public boolean releaseLock(String key) {
        try {
            ReentrantLock lock = lockCache.getIfPresent(key);
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
                if (log.isDebugEnabled()) {
                    log.debug("Caffeine lock released, key={}", key);
                }
                return true;
            }
            if (log.isDebugEnabled()) {
                log.debug("Caffeine lock release failed, lock not held by current thread, key={}", key);
            }
            return false;
        } catch (Exception e) {
            log.warn("Caffeine lock release error, key:{}", key, e);
            return false;
        }
    }

    /**
     * 阻塞获取锁并执行（无返回值）
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间 - 注意：Caffeine实现忽略此参数
     * @param timeUnit 时间单位
     * @param action   需要执行的方法（无返回值）
     * @return true-获取锁成功并执行完成，false-未获取到锁
     */
    @Override
    public boolean tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Runnable action) {
        ReentrantLock lock = lockCache.get(key, k -> new ReentrantLock());
        try {
            if (lock.tryLock(waitTime, timeUnit)) {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("Caffeine tryLockAndRun executing, key={}, waitTime={}, timeUnit={}",
                                key, waitTime, timeUnit);
                    }
                    action.run();
                    return true;
                } catch (Exception e) {
                    // 业务逻辑异常，记录日志并重新抛出
                    log.error("Caffeine tryLockAndRun业务逻辑执行异常, key:{}", key, e);
                    throw e;
                } finally {
                    lock.unlock();
                }
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Caffeine tryLockAndRun failed to acquire lock, key={}", key);
                }
                return false;
            }
        } catch (InterruptedException e) {
            log.error("Caffeine tryLockAndRun interrupted, key:{}", key, e);
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * 阻塞获取锁并执行（有返回值）
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间 - 注意：Caffeine实现忽略此参数
     * @param timeUnit 时间单位
     * @param action   需要执行的方法
     * @param <R>      方法返回值类型
     * @return 锁执行结果
     */
    @Override
    public <R> LockRunResult<R> tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Supplier<R> action) {
        ReentrantLock lock = lockCache.get(key, k -> new ReentrantLock());
        try {
            if (lock.tryLock(waitTime, timeUnit)) {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("Caffeine tryLockAndRun executing, key={}, waitTime={}, timeUnit={}", key, waitTime, timeUnit);
                    }
                    R result = action.get();
                    return LockRunResult.buildSuccess(result);
                } catch (Exception e) {
                    // 业务逻辑执行异常
                    log.error("Caffeine tryLockAndRun业务逻辑执行异常, key:{}", key, e);
                    return LockRunResult.buildError(e);
                } finally {
                    lock.unlock();
                }
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Caffeine tryLockAndRun failed to acquire lock, key={}", key);
                }
                return LockRunResult.buildGetLockErr();
            }
        } catch (InterruptedException e) {
            log.error("Caffeine tryLockAndRun interrupted, key:{}", key, e);
            Thread.currentThread().interrupt();
            return LockRunResult.buildError("LOCK_INTERRUPTED", "获取锁被中断", e);
        } catch (Exception e) {
            log.error("Caffeine tryLockAndRun execution error, key:{}", key, e);
            return LockRunResult.buildError("LOCK_ERROR", "获取锁异常", e);
        }
    }
}
