package org.springblade.core.databridge.spi;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;

/**
 * 数据出站 Sink 接口（Strategy Pattern）。
 * <p>
 * 本接口定义了一条统一的"把通用 payload 写入第三方系统"动作。每种协议（Kafka / Redis /
 * RocketMQ / MySQL / HTTP / WebHook / MQTT）实现一个 Sink，业务侧按 {@link ConnectorType}
 * 通过 {@code ConnectorRegistry} 取对应实现。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>本接口入参 {@link ConnectorPayload} / {@link ConnectorConfig} 全部通用模型，
 *       不感知任何业务字段（productId / deviceId / actionType 等）。</li>
 *   <li>实现侧仅负责"协议适配"，不做限流 / 重试 / 死信 / 日志事件等业务编排
 *       ── 那是业务侧 SinkDispatcher 的职责。</li>
 *   <li>新增协议 = 加一个 Sink 实现类 + ConnectorType 枚举值，业务侧 0 改动。</li>
 * </ul>
 *
 * <h3>线程安全 & 生命周期</h3>
 * <ul>
 *   <li>实现类必须线程安全：<b>同一 Sink 实例被多线程并发 send</b>（业务侧 dispatcher 用线程池）。</li>
 *   <li>底层连接由 {@code ConnectionPoolManager} 按 {@link ConnectorConfig#getIdentifier()} 复用，
 *       Sink 实现内部一般不持有状态，每次 send 时从 pool 拿连接即可。</li>
 *   <li>{@link #close(ConnectorConfig)} 由 pool eviction 自动调用，实现侧释放底层连接资源。</li>
 * </ul>
 *
 * <h3>实现样例</h3>
 * <pre>{@code
 * @Component
 * public class KafkaSink implements Sink {
 *     private final ConnectionPoolManager pool;
 *
 *     @Override public ConnectorType supports() { return ConnectorType.KAFKA; }
 *
 *     @Override
 *     public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
 *         long start = System.currentTimeMillis();
 *         KafkaProducer<byte[], byte[]> producer = pool.getOrCreate(
 *             config.getIdentifier(), config, c -> buildProducer(c));
 *         try {
 *             RecordMetadata m = producer.send(toRecord(payload, config))
 *                 .get(timeout, TimeUnit.MILLISECONDS);
 *             return SendResult.success(
 *                 m.topic() + "-" + m.partition() + "-" + m.offset(),
 *                 System.currentTimeMillis() - start,
 *                 Map.of("partition", m.partition(), "offset", m.offset()));
 *         } catch (Exception e) {
 *             return SendResult.fail(e, System.currentTimeMillis() - start);
 *         }
 *     }
 *
 *     @Override public boolean testConnection(ConnectorConfig config) { ... }
 *     @Override public void close(ConnectorConfig config) { pool.invalidate(config.getIdentifier()); }
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public interface Sink {

    /**
     * 本 Sink 支持的协议类型。
     * <p>{@code ConnectorRegistry} 用此返回值建立 {@code ConnectorType → Sink} 的映射表。
     */
    ConnectorType supports();

    /**
     * 把 payload 写入下游系统。
     * <p><b>实现要求</b>：
     * <ul>
     *   <li>必须线程安全（多线程并发 send 同一 Sink 实例）</li>
     *   <li>不抛 checked exception；任何错误（网络 / 认证 / 序列化）都包成 {@link SendResult#fail(Throwable, long)} 返回</li>
     *   <li>必须计算并填充 {@link SendResult#getLatencyMs()}，用于业务侧监控</li>
     *   <li>成功时填 {@link SendResult#getMessageId()}（协议无 ID 时可为 null）</li>
     * </ul>
     *
     * @param payload 通用 payload（业务侧已序列化好 byte[] body）
     * @param config  连接配置（含 connectionJson / credentialJson / extraConfigJson）
     * @return 发送结果，永不为 null
     */
    SendResult send(ConnectorPayload payload, ConnectorConfig config);

    /**
     * 测试连接是否可达。供数据源 CRUD 表单的"测试连接"按钮使用。
     * <p><b>实现要求</b>：必须有短超时（建议 3 秒以内），不应阻塞调用线程过久。
     *
     * @return true 可达；false 任何原因不可达（网络 / 认证 / topic 不存在等）
     */
    boolean testConnection(ConnectorConfig config);

    /**
     * 释放与本配置关联的底层连接资源。
     * <p>典型场景：
     * <ul>
     *   <li>{@code ConnectionPoolManager} 触发 LRU 淘汰时调用</li>
     *   <li>数据源配置变更后业务侧主动 invalidate</li>
     *   <li>JVM 关闭时</li>
     * </ul>
     * <p>实现要求：幂等（多次调用安全），异常吞掉仅 warn 日志（避免阻塞 cleanup 链）。
     */
    void close(ConnectorConfig config);
}
