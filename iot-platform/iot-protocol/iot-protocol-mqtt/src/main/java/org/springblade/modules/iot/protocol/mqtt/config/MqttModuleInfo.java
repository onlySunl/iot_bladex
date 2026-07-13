

package org.springblade.modules.iot.protocol.mqtt.config;

import org.springblade.modules.iot.common.protocol.ProtocolModuleInfo;
import org.springframework.stereotype.Component;

/**
 * MQTT 协议模块信息
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/2
 */
@Component
public class MqttModuleInfo implements ProtocolModuleInfo {

  @Override
  public String getCode() {
    return "mqtt";
  }

  @Override
  public String getName() {
    return "MQTT";
  }

  @Override
  public String getDescription() {
    return "使用EMQX作为内置MQTT，轻量级的发布/订阅消息传输";
  }

  @Override
  public String getVersion() {
    return "2.0";
  }

  @Override
  public String getVendor() {
    return "Universal IoT";
  }

  @Override
  public boolean isCore() {
    return true; // MQTT是核心协议
  }

  @Override
  public ProtocolCategory getCategory() {
    return ProtocolCategory.MESSAGING;
  }
}
