package org.springblade.modules.iot.protocol.common.protocol;

import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import java.util.List;

/**
 * 协议编解码器接口 - 每种协议需实现此接口
 *
 * @author blade-iot
 */
public interface ProtocolCodec {

    /**
     * 获取协议类型
     */
    ProtocolType getProtocolType();

    /**
     * 解码设备上行数据
     *
     * @param payload 原始字节数据
     * @param topic   消息主题 (MQTT 场景)
     * @return 解码后的设备消息列表
     */
    List<DeviceMessage> decode(byte[] payload, String topic);

    /**
     * 编码平台下发数据
     *
     * @param message 下发消息
     * @return 编码后的字节数据
     */
    byte[] encode(DeviceMessage message);

    /**
     * 是否支持该协议类型
     */
    default boolean supports(ProtocolType type) {
        return getProtocolType() == type;
    }
}
