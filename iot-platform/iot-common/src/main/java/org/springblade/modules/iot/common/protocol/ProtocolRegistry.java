package org.springblade.modules.iot.common.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议注册表 - 管理所有协议编解码器
 *
 * @author blade-iot
 */
public class ProtocolRegistry {

    private static final Map<ProtocolType, ProtocolCodec> CODEC_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ProtocolDefinition> PROTOCOL_MAP = new ConcurrentHashMap<>();

    /**
     * 注册协议编解码器
     */
    public static void registerCodec(ProtocolCodec codec) {
        CODEC_MAP.put(codec.getProtocolType(), codec);
    }

    /**
     * 获取协议编解码器
     */
    public static ProtocolCodec getCodec(ProtocolType type) {
        return CODEC_MAP.get(type);
    }

    /**
     * 注册协议定义
     */
    public static void registerProtocol(ProtocolDefinition definition) {
        PROTOCOL_MAP.put(definition.getId(), definition);
    }

    /**
     * 获取协议定义
     */
    public static ProtocolDefinition getProtocol(String id) {
        return PROTOCOL_MAP.get(id);
    }

    /**
     * 获取所有已注册协议
     */
    public static Map<String, ProtocolDefinition> getAllProtocols() {
        return Map.copyOf(PROTOCOL_MAP);
    }

    /**
     * 移除协议
     */
    public static void unregister(String id) {
        ProtocolDefinition def = PROTOCOL_MAP.remove(id);
        if (def != null) {
            CODEC_MAP.remove(def.getType());
        }
    }
}
