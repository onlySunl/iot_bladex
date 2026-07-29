package org.springblade.core.databridge.serializer;

import java.nio.charset.StandardCharsets;

import org.springblade.core.databridge.spi.Serializer;

/**
 * 二进制透传序列化策略。
 * <p>
 * 调用方<b>已经持有 byte[]</b> 时（如 Protobuf 字节、加密报文、图片字节）直接透传，
 * 不做任何编码转换；反向亦然，{@code deserialize} 直接返回 byte[]。
 * 适用场景：图片 / 文件 / 协议自带二进制编码 / 自管 Schema 的私有协议。
 * </p>
 *
 * <h3>类型约束</h3>
 * <ul>
 *   <li>序列化：仅接受 {@code byte[]}（首选）或 {@code CharSequence}（自动 UTF-8 编码）</li>
 *   <li>反序列化：仅返回 {@code byte[]}（其它类型抛异常）</li>
 * </ul>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public class BinarySerializer implements Serializer {

    public static final String NAME = "BINARY";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return new byte[0];
        }
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof CharSequence) {
            return obj.toString().getBytes(StandardCharsets.UTF_8);
        }
        throw new UnsupportedOperationException(
            "[BinarySerializer] only supports byte[] or CharSequence input, got: " + obj.getClass().getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> type) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        if (type == byte[].class || type == Object.class) {
            return (T) bytes;
        }
        throw new UnsupportedOperationException(
            "[BinarySerializer] only supports byte[] target type, got: " + type.getName());
    }
}
