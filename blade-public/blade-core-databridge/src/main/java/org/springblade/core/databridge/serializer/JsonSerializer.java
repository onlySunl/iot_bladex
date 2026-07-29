package org.springblade.core.databridge.serializer;

import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.spi.Serializer;

/**
 * JSON 序列化策略（默认 / 最常用）。
 * <p>
 * 复用项目通用 {@link JsonUtil}（基于 Jackson），以保持与业务侧（Controller / Echo / Feign）
 * 字段命名 / 日期格式 / null 处理 等行为完全一致。
 * </p>
 *
 * <h3>用法</h3>
 * 业务侧通过 {@link org.springblade.core.databridge.model.ConnectorConfig#getSerialization()}
 * 设为 {@code "JSON"} 选用本实现。
 *
 * <h3>线程安全</h3>
 * 无状态、线程安全。Spring 装配时单例共享。
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public class JsonSerializer implements Serializer {

    /**
     * 策略名（与 ConnectorConfig.serialization 字符串匹配）。
     */
    public static final String NAME = "JSON";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return new byte[0];
        }
        try {
            return JsonUtil.toJsonAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("[JsonSerializer] serialize failed: " + e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return JsonUtil.parse(bytes, type);
        } catch (Exception e) {
            throw new RuntimeException("[JsonSerializer] deserialize failed (type=" + type.getName()
                + ", bytes.length=" + bytes.length + "): " + e.getMessage(), e);
        }
    }
}
