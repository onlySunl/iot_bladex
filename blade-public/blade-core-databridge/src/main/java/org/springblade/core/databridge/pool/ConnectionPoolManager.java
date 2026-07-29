package org.springblade.core.databridge.pool;

import java.io.Closeable;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用连接池管理器（任意 String key 的 Caffeine 缓存）。
 * <p>
 * 各 {@link org.springblade.core.databridge.spi.Sink} / {@link org.springblade.core.databridge.spi.Source}
 * 实现把昂贵的连接对象（KafkaProducer / Lettuce StatefulRedisConnection / DefaultMQProducer /
 * HikariDataSource / OkHttpClient / MqttClient 等）放进本池复用，避免每次 send 都新建连接。
 * </p>
 *
 * <h3>OCP 边界（设计核心）</h3>
 * <ul>
 *   <li><b>key 是任意 String</b>：本池不感知"租户 / 数据源 ID"等业务概念，
 *       业务侧 SinkDispatcher 自由组合 {@code "tenantId:dsId"} / {@code "appId:dsCode"} 等。</li>
 *   <li><b>value 是 Object</b>：池子不感知具体连接类型，泛型 {@code <T>} 仅在 API 层提供类型安全</li>
 *   <li><b>不感知协议</b>：每个协议的 Sink/Source 自己负责 factory（怎么建连接）+ closer（怎么关），
 *       池子只管"复用 / 淘汰 / 清理"三件事</li>
 * </ul>
 *
 * <h3>容量 & 淘汰策略</h3>
 * <ul>
 *   <li>{@code maximumSize=1000}：单实例最多缓存 1000 个连接对象，超出 LRU 淘汰</li>
 *   <li>{@code expireAfterAccess=30min}：30 分钟无访问自动淘汰（防长时间空闲连接占用资源）</li>
 *   <li>淘汰时 {@link RemovalListener} 自动调用 connector 的 close 方法（{@link Closeable} 或反射 close()）</li>
 * </ul>
 *
 * <h3>线程安全</h3>
 * Caffeine 本身线程安全；同一 key 并发 {@link #getOrCreate(String, Object, Function)} 时
 * factory 仅执行一次（其它线程阻塞等待）── 即"加载锁"由 Caffeine 内部保证。
 *
 * <h3>典型用法（业务侧 SinkDispatcher 拼 key）</h3>
 * <pre>{@code
 * // 业务侧 SinkDispatcher
 * String poolKey = env.getTenantId() + ":" + dataSource.getId();
 * KafkaProducer<byte[], byte[]> producer = pool.getOrCreate(
 *     poolKey,
 *     dataSource,
 *     ds -> KafkaSinkFactory.buildProducer(ds));
 *
 * producer.send(record);   // 复用同一 producer 实例
 *
 * // 数据源配置变更后
 * pool.invalidate(poolKey);   // 旧连接自动 close
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
public class ConnectionPoolManager {

    /**
     * 默认最大缓存条目（超出 LRU 淘汰）。可通过构造器自定义。
     */
    public static final int DEFAULT_MAX_SIZE = 1000;

    /**
     * 默认空闲过期时间。可通过构造器自定义。
     */
    public static final Duration DEFAULT_EXPIRE_AFTER_ACCESS = Duration.ofMinutes(30);

    /**
     * key → 连接对象的核心缓存。value 是 Object（各 Sink 自己 cast 为具体类型）。
     */
    private final Cache<String, Object> cache;

    /**
     * 监控指标：成功复用次数（hit）。
     */
    private final AtomicLong hitCount = new AtomicLong();

    /**
     * 监控指标：新建连接次数（factory invoked）。
     */
    private final AtomicLong loadCount = new AtomicLong();

    /**
     * 监控指标：被淘汰次数（LRU / TTL / 手动 invalidate）。
     */
    private final AtomicLong evictCount = new AtomicLong();

    public ConnectionPoolManager() {
        this(DEFAULT_MAX_SIZE, DEFAULT_EXPIRE_AFTER_ACCESS);
    }

    public ConnectionPoolManager(int maximumSize, Duration expireAfterAccess) {
        this.cache = Caffeine.newBuilder()
            .maximumSize(maximumSize)
            .expireAfterAccess(expireAfterAccess)
            .removalListener((RemovalListener<String, Object>) (key, value, cause) -> {
                evictCount.incrementAndGet();
                log.debug("[ConnectionPool] evict key={} cause={}", key, cause);
                closeQuietly(key, value);
            })
            .build();
        log.info("[ConnectionPool] initialized: maxSize={} expireAfterAccess={}",
            maximumSize, expireAfterAccess);
    }

    // ============================== 核心 API ==============================

    /**
     * 取已存在的连接，缺则用 factory 创建并缓存。
     * <p><b>线程安全</b>：同一 key 并发调用，factory 仅执行 1 次（其它线程等待）。
     *
     * @param key     任意 String key（业务侧自由组合，本池不感知语义）
     * @param input   传给 factory 的输入对象（一般是 ConnectorConfig，本类用泛型 K 解耦）
     * @param factory 首次创建连接的工厂函数
     * @param <K>     factory 入参类型
     * @param <V>     连接对象类型
     * @return 缓存中的连接（永不为 null，除非 factory 显式返回 null 此时上层通常应抛异常）
     */
    @SuppressWarnings("unchecked")
    public <K, V> V getOrCreate(String key, K input, Function<K, V> factory) {
        Object value = cache.getIfPresent(key);
        if (value != null) {
            hitCount.incrementAndGet();
            return (V) value;
        }
        // miss → 用 factory 创建（Caffeine 的 get(K, Function) 会保证同 key 串行加载）
        return (V) cache.get(key, k -> {
            loadCount.incrementAndGet();
            log.debug("[ConnectionPool] miss → loading new connection key={}", k);
            V created = factory.apply(input);
            if (created == null) {
                log.warn("[ConnectionPool] factory returned null for key={}, will not be cached", k);
            }
            return created;
        });
    }

    /**
     * 主动失效 key 对应的连接（触发 RemovalListener 自动 close）。
     * <p>典型场景：数据源配置变更、健康检查失败、业务侧主动断开。
     */
    public void invalidate(String key) {
        if (key == null) {
            return;
        }
        cache.invalidate(key);
        log.debug("[ConnectionPool] invalidate key={}", key);
    }

    /**
     * 失效所有连接（应用关闭时调用，确保连接资源释放）。
     */
    public void invalidateAll() {
        long before = cache.estimatedSize();
        cache.invalidateAll();
        log.info("[ConnectionPool] invalidateAll cleared {} entries", before);
    }

    // ============================== 监控指标 ==============================

    /**
     * 当前缓存条目数（近似值，Caffeine 不保证精确）。
     */
    public long size() {
        return cache.estimatedSize();
    }

    public long getHitCount() {
        return hitCount.get();
    }

    public long getLoadCount() {
        return loadCount.get();
    }

    public long getEvictCount() {
        return evictCount.get();
    }

    /**
     * 命中率（百分比）。供监控 / 大盘展示。
     */
    public double hitRate() {
        long hit = hitCount.get();
        long total = hit + loadCount.get();
        return total == 0 ? 0d : (hit * 100.0d / total);
    }

    // ============================== 内部：安全关闭连接 ==============================

    /**
     * 尝试调用连接对象的 close() 方法释放资源。
     * <ul>
     *   <li>{@link AutoCloseable} → 直接调 close()</li>
     *   <li>无 close 方法或异常 → 仅 warn 日志，不阻塞 cleanup 链</li>
     * </ul>
     */
    private void closeQuietly(String key, Object value) {
        if (value == null) {
            return;
        }
        try {
            if (value instanceof AutoCloseable) {
                ((AutoCloseable) value).close();
                log.debug("[ConnectionPool] closed AutoCloseable connection key={}", key);
            } else {
                // 非 Closeable 时尝试反射调 close()（如 KafkaProducer.close 不是 Closeable 但有 close 方法）
                java.lang.reflect.Method close;
                try {
                    close = value.getClass().getMethod("close");
                    close.invoke(value);
                    log.debug("[ConnectionPool] closed via reflection key={}", key);
                } catch (NoSuchMethodException nsme) {
                    log.debug("[ConnectionPool] no close() method on {} (key={})",
                        value.getClass().getSimpleName(), key);
                }
            }
        } catch (Throwable t) {
            log.warn("[ConnectionPool] close failed for key={} type={}: {}",
                key, value.getClass().getSimpleName(), t.getMessage());
        }
    }
}
