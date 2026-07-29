package org.springblade.core.databridge.spi;

/**
 * 通用序列化策略接口（Strategy Pattern）。
 * <p>
 * 业务侧把对象序列化为 byte[] 塞进 {@link org.springblade.core.databridge.model.ConnectorPayload#getBody()}
 * 之前（或 Source 把 byte[] 反序列化回对象之后）会用到本接口的实现。
 * </p>
 * <p>
 * 当前默认实现见 {@code serializer/} 子包：JSON / STRING / BINARY / AVRO（占位，按需实现）。
 * 业务侧通过 {@link org.springblade.core.databridge.model.ConnectorConfig#getSerialization()}
 * 字符串与 {@link #name()} 匹配，由 starter 内部选取对应 Serializer 实例。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>新增序列化方式（如 Protobuf / MessagePack）= 加一个 Serializer 实现 + 在 ConnectorConfig.serialization 用新值</li>
 *   <li>实现侧不感知具体业务对象 ── 通过泛型 {@code Class<T>} 或 byte[] 处理任意类型</li>
 * </ul>
 *
 * <h3>线程安全</h3>
 * <p>实现类<b>必须线程安全</b>且<b>无状态</b>。Spring 装配时只创建一个实例供全局共享。
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public interface Serializer {

    /**
     * 策略名（与 {@link org.springblade.core.databridge.model.ConnectorConfig#getSerialization()} 匹配）。
     * <p>建议大写：{@code "JSON" / "AVRO" / "STRING" / "BINARY" / "PROTOBUF"}。
     */
    String name();

    /**
     * 把任意对象序列化为 byte[]。
     * <p><b>实现要求</b>：
     * <ul>
     *   <li>obj 为 null 时返回长度 0 的 byte[]（不要抛异常）</li>
     *   <li>编码失败抛 {@link RuntimeException}（业务侧用 try-catch 包成 SendResult.fail）</li>
     * </ul>
     */
    byte[] serialize(Object obj);

    /**
     * 把 byte[] 反序列化为指定类型对象。
     * <p><b>实现要求</b>：
     * <ul>
     *   <li>bytes 为 null 或长度 0 → 返回 null</li>
     *   <li>解码失败抛 {@link RuntimeException}</li>
     *   <li>{@code BINARY} 等无类型概念的实现可忽略 type 参数直接返回 byte[] 包装</li>
     * </ul>
     */
    <T> T deserialize(byte[] bytes, Class<T> type);
}
