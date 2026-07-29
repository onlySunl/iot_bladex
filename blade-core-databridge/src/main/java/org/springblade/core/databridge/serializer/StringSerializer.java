package org.springblade.core.databridge.serializer;

import java.nio.charset.StandardCharsets;

import org.springblade.core.databridge.spi.Serializer;

/**
 * 纯文本（UTF-8）序列化策略。
 * <p>
 * 把 {@code obj.toString()} 编码为 UTF-8 byte[]，反向解码为 String。
 * 适用场景：HTTP / WebHook 推送已经是 JSON 字符串的 payload；MQTT topic 上发原始文本；
 * Redis SET 键值是字符串等。
 * </p>
 *
 * <h3>反序列化约束</h3>
 * <p>{@link #deserialize(byte[], Class)} 仅支持 {@code String} / {@code CharSequence} 类型，
 * 其它类型抛 {@link UnsupportedOperationException}（语义模糊：byte[] → String → ??? Object）。
 * 如需 byte[] → 业务对象，请改用 {@link JsonSerializer}。
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public class StringSerializer implements Serializer {

    public static final String NAME = "STRING";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return new byte[0];
        }
        return obj.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> type) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        if (type == String.class || type == CharSequence.class || type == Object.class) {
            return (T) str;
        }
        throw new UnsupportedOperationException(
            "[StringSerializer] only supports String/CharSequence target type, got: " + type.getName()
                + "; use JsonSerializer for object deserialization");
    }
}
