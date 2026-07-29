package org.springblade.core.protocol.codec;

import org.springblade.core.protocol.model.DeviceMessage;

/**
 * 协议编解码器接口
 *
 * @author Chill
 */
public interface ProtocolCodec {

    /**
     * 编码消息为字节数组
     *
     * @param message 设备消息
     * @return 编码后的字节数组
     * @throws Exception 编码异常
     */
    byte[] encode(DeviceMessage message) throws Exception;

    /**
     * 解码字节数组为消息
     *
     * @param data 字节数组
     * @return 设备消息
     * @throws Exception 解码异常
     */
    DeviceMessage decode(byte[] data) throws Exception;

    /**
     * 获取支持的协议类型
     *
     * @return 协议类型
     */
    String getProtocolType();
}
