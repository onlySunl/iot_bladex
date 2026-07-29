package org.springblade.core.databridge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用数据桥接连接器配置。
 * <p>
 * 本类是 Sink / Source 实例化所需的<b>纯连接信息容器</b>，业务侧把已解密的连接 + 凭证 +
 * 协议特异参数填进来传给 starter；starter 内部依据 {@link #type} 找到对应实现并解析
 * {@link #connectionJson} / {@link #credentialJson} / {@link #extraConfigJson} 中的字段。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>不持有租户 / 业务规则 / 数据源实体引用</li>
 *   <li>仅 String + 枚举字段，便于跨进程传递（亦可 JSON 序列化做远程 SinkPool 等扩展）</li>
 *   <li>各协议特异字段全部塞 JSON 字符串，starter 内部按需解析；新增协议只加 enum + Sink 实现</li>
 * </ul>
 *
 * <h3>字段说明</h3>
 * <table>
 *   <tr><th>字段</th><th>用途</th><th>典型示例</th></tr>
 *   <tr><td>{@link #type}</td><td>协议类型</td><td>{@link ConnectorType#KAFKA}</td></tr>
 *   <tr><td>{@link #identifier}</td><td>配置标识，调用方拼 pool key 用</td>
 *       <td>{@code "ds-12345"} 或业务侧 {@code "tenant-001:ds-12345"}</td></tr>
 *   <tr><td>{@link #connectionJson}</td><td>非敏感连接参数 JSON</td>
 *       <td>{@code {"bootstrapServers":"...","topic":"iot-out"}}</td></tr>
 *   <tr><td>{@link #credentialJson}</td><td>敏感凭证 JSON（调用方传入时已明文解密）</td>
 *       <td>{@code {"saslPassword":"...","saslMechanism":"PLAIN"}}</td></tr>
 *   <tr><td>{@link #extraConfigJson}</td><td>性能 / 可靠性调参 JSON</td>
 *       <td>{@code {"acks":"1","compressionType":"snappy","lingerMs":50}}</td></tr>
 *   <tr><td>{@link #serialization}</td><td>负载序列化策略名（与 {@code Serializer} 实现对齐）</td>
 *       <td>{@code "JSON" / "AVRO" / "STRING" / "BINARY"}</td></tr>
 * </table>
 *
 * <h3>使用样例</h3>
 * <pre>{@code
 * ConnectorConfig cfg = ConnectorConfig.builder()
 *     .type(ConnectorType.KAFKA)
 *     .identifier("ds-12345")
 *     .connectionJson("{\"bootstrapServers\":\"127.0.0.1:9092\",\"topic\":\"iot-out\"}")
 *     .credentialJson("{}")
 *     .extraConfigJson("{\"acks\":\"1\",\"compressionType\":\"snappy\"}")
 *     .serialization("JSON")
 *     .build();
 *
 * Sink sink = registry.getSink(cfg.getType());
 * SendResult r = sink.send(payload, cfg);
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectorConfig {

    /**
     * 协议类型（决定走哪个 Sink/Source 实现）。
     */
    private ConnectorType type;

    /**
     * 配置标识符。
     * <p><b>仅作 ConnectionPoolManager 中的 key 组成片段使用</b>；具体 key 怎么拼由调用方决定。
     * 如业务侧多租户场景常拼 {@code "tenantId:dataSourceId"}（util 不感知 tenant 概念）。
     */
    private String identifier;

    /**
     * 非敏感连接参数 JSON（host / port / topic / database / mode 等，业务侧从 DB 读出明文）。
     */
    private String connectionJson;

    /**
     * 敏感凭证 JSON（password / token / AK/SK / 私钥；调用方需在传入前解密）。
     */
    private String credentialJson;

    /**
     * 协议特异调参 JSON（acks / compression / timeout / pool size 等）。
     */
    private String extraConfigJson;

    /**
     * 负载序列化策略名。
     * <p>对应 {@code org.springblade.core.databridge.spi.Serializer.name()} 返回值。
     * 当前可选：{@code "JSON" / "AVRO" / "STRING" / "BINARY"}（详见 serializer/ 子包）。
     */
    @Builder.Default
    private String serialization = "JSON";
}
