package org.springblade.core.databridge.model;

import java.util.Collections;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用数据桥接 payload（写入下游 Sink 用）。
 * <p>
 * 本类是 {@code blade-core-databridge} 对外暴露的唯一消息容器，
 * <b>仅持有 byte[] / Map / String / long</b>，<b>0 业务字段</b>。业务侧
 * （如 IoT 桥接的 BridgeMessageEnvelope）需自行序列化为 byte[] 后填充本对象的 body。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>本类不含 productId / deviceId / actionType / topic 等业务字段</li>
 *   <li>不感知租户 / 不感知规则 / 不感知 IoT 协议</li>
 *   <li>非 IoT 业务复用本 starter 时同样适用</li>
 * </ul>
 *
 * <h3>字段说明</h3>
 * <table>
 *   <tr><th>字段</th><th>用途</th><th>典型示例</th></tr>
 *   <tr><td>{@link #body}</td><td>实际负载字节数组</td>
 *       <td>JSON UTF-8 字节 / Avro 编码字节 / 原始二进制</td></tr>
 *   <tr><td>{@link #headers}</td><td>消息头 / 元数据</td>
 *       <td>Kafka headers / HTTP headers / RocketMQ properties / MQTT user properties (v5)</td></tr>
 *   <tr><td>{@link #routingKey}</td><td>路由键</td>
 *       <td>Kafka 分区 hash 键 / RocketMQ 顺序消息 hashKey / MQTT 子 topic 占位 / Redis key</td></tr>
 *   <tr><td>{@link #ts}</td><td>事件发生时间 (epoch millis)</td>
 *       <td>设备 publish 时间戳；Sink 内部可作为 record timestamp 使用</td></tr>
 * </table>
 *
 * <h3>典型用法（业务侧填充）</h3>
 * <pre>{@code
 * // rule-biz/SinkDispatcher
 * ConnectorPayload payload = ConnectorPayload.builder()
 *     .body(JsonUtil.toJsonAsBytes(envelope))
 *     .headers(Map.of(
 *         "X-Tenant", envelope.getTenantId(),
 *         "X-Trace",  envelope.getTraceId()))
 *     .routingKey(envelope.getDeviceIdentification())
 *     .ts(envelope.getTs())
 *     .build();
 *
 * SendResult r = sink.send(payload, config);
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectorPayload {

    /**
     * 实际负载字节（业务侧负责序列化）。允许 null（仅当 Sink 协议支持空 body，如 HTTP HEAD / Redis DEL）。
     */
    private byte[] body;

    /**
     * 消息头 / 元数据。允许 null（等价空 map）。Key 大小写敏感，建议 {@code X-Thinglinks-} 前缀避免与协议头冲突。
     */
    private Map<String, String> headers;

    /**
     * 路由键。可空。各 Sink 用法不同，详见 class javadoc 字段说明表。
     */
    private String routingKey;

    /**
     * 事件发生时间（epoch millis）。0 表示未指定。
     */
    private long ts;

    // ============================== 便捷工具方法 ==============================

    /**
     * 安全获取 headers（永不返回 null，便于链式调用）。
     */
    public Map<String, String> safeHeaders() {
        return headers == null ? Collections.emptyMap() : headers;
    }

    /**
     * 安全读取 header value，缺省返回 null。
     */
    public String header(String key) {
        return headers == null ? null : headers.get(key);
    }

    /**
     * body 字节长度（null-safe）。
     */
    public int size() {
        return body == null ? 0 : body.length;
    }
}
