package org.springblade.core.databridge.serializer;

import org.springblade.core.databridge.spi.Serializer;

/**
 * Avro 序列化占位实现。
 * <p>
 * <b>当前未启用</b>，调用即抛 {@link UnsupportedOperationException}。
 * 保留本类是为了：
 * </p>
 * <ol>
 *   <li>让 {@code ConnectorConfig.serialization = "AVRO"} 在表单字典里有意义</li>
 *   <li>未来按需启用时只需改本类实现，业务侧 0 改动（OCP）</li>
 * </ol>
 *
 * <h3>启用步骤（未来扩展）</h3>
 * <ol>
 *   <li>pom 加 {@code org.apache.avro:avro} + {@code io.confluent:kafka-avro-serializer}（如走 Schema Registry）</li>
 *   <li>实现 {@link #serialize(Object)} / {@link #deserialize(byte[], Class)}：用 {@code DatumWriter / DatumReader}</li>
 *   <li>新增 {@code AvroSchemaRegistry} 配置（schema 来源 / 缓存）</li>
 *   <li>解决泛型对象 ↔ Avro {@code GenericRecord} 映射（用 reflect 或预编译 schema）</li>
 * </ol>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public class AvroSerializer implements Serializer {

    public static final String NAME = "AVRO";

    private static final String NOT_IMPL_MSG =
        "[AvroSerializer] not yet implemented. "
            + "If you need Avro, integrate Schema Registry and implement serialize/deserialize. "
            + "Currently use 'JSON' for object payload, 'BINARY' for raw bytes.";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public byte[] serialize(Object obj) {
        throw new UnsupportedOperationException(NOT_IMPL_MSG);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) {
        throw new UnsupportedOperationException(NOT_IMPL_MSG);
    }
}
