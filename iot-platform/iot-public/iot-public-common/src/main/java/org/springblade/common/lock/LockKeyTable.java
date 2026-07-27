package org.springblade.common.lock;

/**
 * 分布式锁 Key 常量定义
 * <p>
 * 锁 Key 格式: {PREFIX}:lock:{modular}:{table}:{field}:{value}
 * 示例: CacheKey前缀:lock:link:device:id:123
 *
 * @author mqttsnet
 * @date 2025/2/28
 */
public interface LockKeyTable {

    /**
     * 锁前缀标识
     */
    String LOCK_PREFIX = "lock";
}