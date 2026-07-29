package org.springblade.core.databridge.model;

import java.util.Collections;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.basic.utils.SnowflakeIdUtil;

/**
 * Source 拉取到的通用消息封装（{@code Source.start(...)} 回调入参）。
 * <p>
 * 与 {@link ConnectorPayload}（Sink 出方向）对称，本类用于<b>入方向</b> ──
 * 各 Source 实现（Kafka / MQTT / HTTP）从外部系统收到一条消息后，统一封装为
 * {@link SourceMessage} 回调给业务侧消费。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>仅 byte[] / Map / String / long 字段，<b>0 业务字段</b></li>
 *   <li>不感知是哪种业务（IoT 桥接 / 通用数据集成 / 其它）</li>
 *   <li>业务侧自行从 body 反序列化为业务对象（如 BridgeMessageEnvelope）</li>
 * </ul>
 *
 * <h3>字段说明</h3>
 * <table>
 *   <tr><th>字段</th><th>用途</th><th>典型示例</th></tr>
 *   <tr><td>{@link #body}</td><td>原始消息字节</td>
 *       <td>Kafka record value / MQTT publish payload / HTTP body</td></tr>
 *   <tr><td>{@link #headers}</td><td>消息头 / 元数据</td>
 *       <td>Kafka headers / MQTT user property / HTTP headers</td></tr>
 *   <tr><td>{@link #routingKey}</td><td>路由键 / 来源键</td>
 *       <td>Kafka record key / MQTT topic / HTTP query 主键</td></tr>
 *   <tr><td>{@link #ts}</td><td>消息时间戳 (epoch millis)</td>
 *       <td>Kafka record timestamp / MQTT publish 时间 / HTTP 接收时间</td></tr>
 *   <tr><td>{@link #sourceMessageId}</td><td>来源唯一 ID（断点续拉用）</td>
 *       <td>Kafka {@code "topic-partition-offset"} / MQTT messageId / HTTP X-Request-Id</td></tr>
 *   <tr><td>{@link #attributes}</td><td>协议特定扩展</td>
 *       <td>{@code partition / offset / qos / clientId / remoteIp 等}</td></tr>
 * </table>
 *
 * <h3>典型用法（业务侧 Source 回调）</h3>
 * <pre>{@code
 * // rule-biz/SubscriptionSourceManager
 * source.start(config, sourceMsg -> {
 *     // 1. 业务反序列化（util 不感知）
 *     ExternalEvent ev = JsonUtil.parse(new String(sourceMsg.getBody()), ExternalEvent.class);
 *
 *     // 2. 业务字段映射 → 平台 BridgeMessageEnvelope
 *     BridgeMessageEnvelope env = mapper.map(ev, sourceMsg);
 *
 *     // 3. 投 RocketMQ ingress topic
 *     rocketmqTemplate.syncSend(INGRESS_TOPIC, env);
 *
 *     // 4. （断点续拉场景）记录 sourceMessageId 到 subscription_source.last_consume_offset
 *     subscriptionService.updateOffset(srcCode, sourceMsg.getSourceMessageId());
 * });
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SourceMessage {

    /**
     * 原始消息字节（业务侧负责反序列化）。允许 null（仅 HEAD 类入站等极少数情况）。
     */
    private byte[] body;

    /**
     * 消息头 / 元数据。允许 null（等价空 map）。
     */
    private Map<String, String> headers;

    /**
     * 路由键 / 来源键。可空。
     */
    private String routingKey;

    /**
     * 消息时间戳（epoch millis）。0 表示未指定。
     */
    private long ts;

    /**
     * 来源唯一 ID（断点续拉 / 幂等去重 / 链路追踪用）。
     * <p><b>默认值</b>：通过 builder 构造时若未显式指定，自动用项目雪花 ID 工具
     * {@link SnowflakeIdUtil#nextId()} 生成 16 位字符串（保证全局唯一 + 趋势递增）。
     * <p>各协议 Source 实现的取值建议：
     * <ul>
     *   <li>Kafka: 显式塞 {@code topic-partition-offset}（业务侧据此记录 offset 实现 Exactly-Once，覆盖默认雪花）</li>
     *   <li>MQTT QoS 1/2: 显式塞协议 messageId（覆盖默认雪花）</li>
     *   <li>MQTT QoS 0: 不塞，用默认雪花作幂等键</li>
     *   <li>HTTP: 优先用请求头 X-Request-Id；缺失时用默认雪花</li>
     * </ul>
     */
    @Builder.Default
    private String sourceMessageId = SnowflakeIdUtil.nextId();

    /**
     * 协议特定扩展（partition / offset / qos / clientId / remoteIp 等）。
     */
    private Map<String, Object> attributes;

    // ============================== 便捷工具 ==============================

    /**
     * 安全获取 headers（永不返回 null）。
     */
    public Map<String, String> safeHeaders() {
        return headers == null ? Collections.emptyMap() : headers;
    }

    /**
     * 安全获取 attributes（永不返回 null）。
     */
    public Map<String, Object> safeAttributes() {
        return attributes == null ? Collections.emptyMap() : attributes;
    }

    /**
     * body 字节长度（null-safe）。
     */
    public int size() {
        return body == null ? 0 : body.length;
    }

    /**
     * 安全读 header value，缺省 null。
     */
    public String header(String key) {
        return headers == null ? null : headers.get(key);
    }
}
