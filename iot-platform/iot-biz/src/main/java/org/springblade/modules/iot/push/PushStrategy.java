package org.springblade.modules.iot.push;

import org.springblade.modules.iot.protocol.common.message.DeviceMessage;

/**
 * 数据推送策略接口 - 将设备数据推送到外部系统
 *
 * @author blade-iot
 */
public interface PushStrategy {

    /**
     * 获取策略类型标识
     */
    String getType();

    /**
     * 初始化推送策略
     *
     * @param config 配置 (JSON 字符串)
     */
    void init(String config);

    /**
     * 推送设备消息
     *
     * @param message 设备消息
     * @return 是否推送成功
     */
    boolean push(DeviceMessage message);

    /**
     * 关闭推送策略，释放资源
     */
    void close();

    /**
     * 是否可用
     */
    boolean isAvailable();
}
