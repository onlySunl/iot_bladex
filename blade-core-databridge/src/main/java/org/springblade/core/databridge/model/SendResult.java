package org.springblade.core.databridge.model;

import java.util.Collections;
import java.util.Map;

import org.springblade.core.databridge.spi.SinkErrors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sink 发送结果（专用值对象，<b>非</b> 业务通用 R 包装）。
 * <p>
 * 本类是 {@code Sink.send(...)} 的统一返回类型，包含发送是否成功、底层协议返回 ID、
 * 端到端延迟、失败原因等字段。设计上<b>避免</b>使用业务层的 {@code com.mqttsnet.basic.base.R}
 * 包装，原因：
 * </p>
 * <ul>
 *   <li>R 是 HTTP / RPC 边界契约，含 path / extra / Swagger 注解等业务框架字段，
 *       与 SDK 内部返回值语义错位</li>
 *   <li>Util starter 严格遵循 OCP，不应被业务框架的 R 演进绑住</li>
 *   <li>Kafka {@code RecordMetadata} / RocketMQ {@code SendResult} 等行业 SDK 都用专用值对象</li>
 *   <li>性能：R 字段比专用值对象多一倍，每秒万级 send 浪费可观</li>
 * </ul>
 * <p>
 * 业务侧 Controller / 监听器需要 R 包装时，自行 {@code R.success(sendResult)} 即可。
 * </p>
 *
 * <h3>字段说明</h3>
 * <table>
 *   <tr><th>字段</th><th>类型</th><th>说明</th></tr>
 *   <tr><td>{@link #success}</td><td>boolean</td>
 *       <td>⭐ 唯一布尔判断点；true=Sink ack 成功，false=任意原因失败</td></tr>
 *   <tr><td>{@link #messageId}</td><td>String</td>
 *       <td>底层协议返回 ID（Kafka offset 串 / RocketMQ msgId / MQTT messageId / null=协议无 ID）</td></tr>
 *   <tr><td>{@link #latencyMs}</td><td>long</td>
 *       <td>端到端耗时（构造 → ack 返回）；监控 / SLA 计量用</td></tr>
 *   <tr><td>{@link #error}</td><td>Throwable</td>
 *       <td>失败时填；业务侧用于重试 / 死信策略判断（IOException 重试，认证失败不重试等）</td></tr>
 *   <tr><td>{@link #errorCode}</td><td>String</td>
 *       <td>协议特定错误码（HTTP status / Kafka error / MQTT reason code）；可空</td></tr>
 *   <tr><td>{@link #attributes}</td><td>Map</td>
 *       <td>协议扩展（Kafka {@code partition/offset}, RocketMQ {@code queueId/storeTimestamp},
 *       HTTP {@code responseHeaders} 等）；类型 {@code Object} 给各 Sink 灵活塞</td></tr>
 * </table>
 *
 * <h3>典型用法</h3>
 * <pre>{@code
 * // Sink 实现侧
 * try {
 *     long start = System.currentTimeMillis();
 *     RecordMetadata m = producer.send(record).get(timeout, MILLISECONDS);
 *     return SendResult.success(
 *         m.topic() + "-" + m.partition() + "-" + m.offset(),
 *         System.currentTimeMillis() - start,
 *         Map.of("partition", m.partition(), "offset", m.offset()));
 * } catch (Exception e) {
 *     return SendResult.fail(e, System.currentTimeMillis() - start);
 * }
 *
 * // 业务侧消费
 * SendResult r = sink.send(payload, config);
 * if (r.isSuccess()) { ... } else { handleFailure(r.getError()); }
 *
 * // Controller 边界包 R
 * return r.isSuccess() ? R.success(r) : R.fail(r.getError().getMessage());
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendResult {

    /**
     * 是否成功（唯一判断点）。
     */
    private boolean success;

    /**
     * 底层协议返回的消息 ID（无则 null）。
     */
    private String messageId;

    /**
     * 端到端耗时（毫秒）。
     */
    private long latencyMs;

    /**
     * 失败原因（成功时为 null）。
     */
    private Throwable error;

    /**
     * 协议特定错误码（HTTP status / Kafka errorCode / MQTT reason code）。可空。
     */
    private String errorCode;

    /**
     * 协议扩展属性（partition / offset / responseHeaders 等）。
     */
    private Map<String, Object> attributes;

    // ============================== 静态工厂（推荐入口）==============================

    /**
     * 成功（无扩展属性）。
     */
    public static SendResult success(String messageId, long latencyMs) {
        return success(messageId, latencyMs, null);
    }

    /**
     * 成功（带扩展属性）。
     */
    public static SendResult success(String messageId, long latencyMs, Map<String, Object> attributes) {
        return SendResult.builder()
            .success(true)
            .messageId(messageId)
            .latencyMs(latencyMs)
            .attributes(attributes == null ? Collections.emptyMap() : attributes)
            .build();
    }

    /**
     * 失败（仅异常）。
     */
    public static SendResult fail(Throwable error, long latencyMs) {
        return fail(null, error, latencyMs);
    }

    /**
     * 失败（带协议错误码）。
     */
    public static SendResult fail(String errorCode, Throwable error, long latencyMs) {
        return SendResult.builder()
            .success(false)
            .errorCode(errorCode)
            .error(error)
            .latencyMs(latencyMs)
            .build();
    }

    // ============================== 便捷工具 ==============================

    /**
     * 安全获取 attributes（永不返回 null）。
     */
    public Map<String, Object> safeAttributes() {
        return attributes == null ? Collections.emptyMap() : attributes;
    }

    /**
     * 失败时返回错误消息;成功时返回 null(避免上层把 "unknown" 传给前端造成误导)。
     * <p>
     * <b>关键</b>:遍历整个 cause chain 拼接,而不是只取 outer message
     * (统一走 {@link SinkErrors#causeChain})。
     * 因为很多 client(如 RocketMQ MQClientException)外层只写 "Send [3] times, still failed",
     * 真正的 root cause(如 "connect to 172.18.0.x:10909 failed")在 e.getCause() 里。
     * 之前只取 e.getMessage() 会把根因吞掉,排查时极易误判。
     * </p>
     */
    public String errorMessage() {
        if (success) {
            return null;
        }
        if (error == null) {
            return errorCode == null ? "unknown" : errorCode;
        }
        return SinkErrors.causeChain(error);
    }
}
