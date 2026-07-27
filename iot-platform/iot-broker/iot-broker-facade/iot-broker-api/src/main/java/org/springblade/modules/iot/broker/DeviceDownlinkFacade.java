package org.springblade.modules.iot.broker;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.vo.query.DownlinkCommand;

/**
 * 设备下行派发 ── 开放接口 API。业务侧(mqs / link)组完 {@link DownlinkCommand} 调本接口,
 * broker 按协议类型分流到 MQTT(BifroMQ)/ WebSocket(会话直推)/ … 通道。
 *
 * <p>对标 {@link MqttBrokerOpenInnerFacade}:cloud 部署经 Feign 调 broker,boot 部署本地直调 broker-biz。
 *
 * @author mqttsnet
 */
public interface DeviceDownlinkFacade {

    /**
     * 按协议类型分流下发。
     *
     * @param command 协议无关下行命令
     * @return broker 投递结果;失败以 {@link R#getIsSuccess()}=false 体现
     */
    R<?> dispatch(DownlinkCommand command);
}
