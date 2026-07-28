package org.springblade.core.cache.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁
 *
 * @author Chill
 */
@Slf4j
@Component
public class DistributedLock {

    @Autowired(required = false)
    private RedissonClient redissonClient;

    /**
     * 尝试获取锁并执行
     */
    public <T> LockRunResult<T> tryLock(String lockKey, long waitTime, long leaseTime, Supplier<T> supplier) {
        if (redissonClient == null) {
            log.warn("RedissonClient 未配置，跳过分布式锁");
            return LockRunResult.success(supplier.get());
        }

        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean acquired = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            if (acquired) {
                try {
                    return LockRunResult.success(supplier.get());
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            } else {
                return LockRunResult.fail("获取锁失败");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return LockRunResult.fail("获取锁被中断");
        }
    }

    /**
     * 尝试获取锁并执行（无返回值）
     */
    public LockRunResult<Void> tryLock(String lockKey, long waitTime, long leaseTime, Runnable runnable) {
        return tryLock(lockKey, waitTime, leaseTime, () -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 立即获取锁
     */
    public <T> LockRunResult<T> lock(String lockKey, long leaseTime, Supplier<T> supplier) {
        return tryLock(lockKey, 0, leaseTime, supplier);
    }
}
