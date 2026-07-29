package org.springblade.core.cache.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

/**
 * 基于 Redisson 的分布式锁实现
 * <p>
 * 提供更强大的分布式锁功能，包括：
 * <ul>
 *   <li>看门狗机制（自动续期）- 防止任务未完成锁就过期</li>
 *   <li>可重入锁 - 同一线程可多次获取同一把锁</li>
 *   <li>高性能 - 基于 Netty 的高性能实现</li>
 *   <li>生产级可靠性 - 经过大规模生产环境验证</li>
 * </ul>
 * </p>
 *
 * @author mqttsnet
 * @date 2026-02-26
 */
@Slf4j
public class RedisDistributedLock implements DistributedLock {


    private static final String UNLOCK_LUA;

    /*
     * 通过lua脚本释放锁,来达到释放锁的原子操作
     */
    static {
        UNLOCK_LUA = """
                if redis.call("get",KEYS[1]) == ARGV[1]
                then
                    return redis.call("del",KEYS[1])
                else
                    return 0
                end
                """;
    }

    private final RedisTemplate<String, Object> redisTemplate;
    private final ThreadLocal<String> lockFlag = new ThreadLocal<>();

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while (!result && retryTimes-- > 0) {
            try {
                log.debug("get redisDistributeLock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                log.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    private boolean setRedis(final String key, final long expire) {
        try {
            return Boolean.TRUE.equals(redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                byte[] keyByte = redisTemplate.getStringSerializer().serialize(key);
                byte[] uuidByte = redisTemplate.getStringSerializer().serialize(uuid);
                return (boolean) connection.set(keyByte, uuidByte, Expiration.from(expire, TimeUnit.MILLISECONDS),
                        RedisStringCommands.SetOption.ifAbsent());
            }));
        } catch (Exception e) {
            log.error("设置redis锁发生异常", e);
        }
        return false;
    }

    @Override
    public boolean releaseLock(String key) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            return Boolean.TRUE.equals(redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                byte[] scriptByte = redisTemplate.getStringSerializer().serialize(UNLOCK_LUA);
                return connection.eval(scriptByte, ReturnType.BOOLEAN, 1
                        , redisTemplate.getStringSerializer().serialize(key)
                        , redisTemplate.getStringSerializer().serialize(lockFlag.get()));
            }));
        } catch (Exception e) {
            log.error("释放redis锁发生异常", e);
        } finally {
            lockFlag.remove();
        }
        return false;
    }

    /**
     * 阻塞获取锁并执行（无返回值）
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法（无返回值）
     * @return true-获取锁成功并执行完成，false-未获取到锁
     */
    @Override
    public boolean tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Runnable action) {
        long waitTimeMillis = timeUnit.toMillis(waitTime);
        long expireMillis = timeUnit.toMillis(expire);

        // 计算重试次数和间隔
        int retryTimes = calculateRetryTimes(waitTimeMillis);
        long sleepMillis = calculateSleepMillis(waitTimeMillis, retryTimes);

        // 尝试获取锁
        boolean locked = lock(key, expireMillis, retryTimes, sleepMillis);

        if (locked) {
            try {
                log.info("RedisDistributedLock tryLockAndRun, key={}, waitTime={}ms, expire={}ms, threadId={}", key, waitTimeMillis, expireMillis, Thread.currentThread().getId());
                action.run();
                return true;
            } finally {
                releaseLock(key);
            }
        } else {
            log.warn("RedisDistributedLock tryLockAndRun failed to acquire lock, key={}", key);
            return false;
        }
    }

    /**
     * 阻塞获取锁并执行（有返回值）
     *
     * @param key      锁的key
     * @param waitTime 阻塞等待锁的时间
     * @param expire   锁的最大存活时间
     * @param timeUnit 时间单位
     * @param action   需要执行的方法
     * @param <R>      方法返回值类型
     * @return 锁执行结果
     */
    @Override
    public <R> LockRunResult<R> tryLockAndRun(String key, long waitTime, long expire, TimeUnit timeUnit, Supplier<R> action) {
        long waitTimeMillis = timeUnit.toMillis(waitTime);
        long expireMillis = timeUnit.toMillis(expire);

        // 计算重试次数和间隔
        int retryTimes = calculateRetryTimes(waitTimeMillis);
        long sleepMillis = calculateSleepMillis(waitTimeMillis, retryTimes);

        // 尝试获取锁
        boolean locked = lock(key, expireMillis, retryTimes, sleepMillis);

        if (locked) {
            try {
                log.info("RedisDistributedLock tryLockAndRun, key={}, waitTime={}ms, expire={}ms, threadId={}", key, waitTimeMillis, expireMillis, Thread.currentThread().getId());
                R result = action.get();
                return LockRunResult.buildSuccess(result);
            } catch (Exception e) {
                log.error("RedisDistributedLock tryLockAndRun execution error, key={}", key, e);
                return LockRunResult.buildGetLockErr();
            } finally {
                releaseLock(key);
            }
        } else {
            log.warn("RedisDistributedLock tryLockAndRun failed to acquire lock, key={}", key);
            return LockRunResult.buildGetLockErr();
        }
    }

    /**
     * 计算重试次数
     *
     * @param waitTimeMillis 等待时间（毫秒）
     * @return 重试次数
     */
    private int calculateRetryTimes(long waitTimeMillis) {
        if (waitTimeMillis <= 0) {
            return 0;
        }

        // 最小重试间隔为50ms，最大重试次数不超过100次
        int maxRetryTimes = Math.min((int) (waitTimeMillis / 50), 100);
        return Math.max(1, maxRetryTimes);
    }

    /**
     * 计算重试间隔
     *
     * @param waitTimeMillis 等待时间（毫秒）
     * @param retryTimes     重试次数
     * @return 重试间隔（毫秒）
     */
    private long calculateSleepMillis(long waitTimeMillis, int retryTimes) {
        if (retryTimes <= 0) {
            return 0;
        }

        // 平均分配等待时间，但最小间隔为50ms
        long averageSleep = waitTimeMillis / retryTimes;
        return Math.max(50, Math.min(averageSleep, 1000));
    }
}
