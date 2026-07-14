

package org.springblade.modules.iot.protocol.mqtt.processor.up;

import org.springblade.modules.iot.protocol.mqtt.entity.MQTTUPRequest;
import org.springblade.modules.iot.protocol.mqtt.processor.up.common.BaseSubscribeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MQTT解码处理器
 *
 * <p>MQTTUPRequest，负责将MQTT协议转换
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/20
 */
@Slf4j(topic = "mqtt")
@Component
public class SubscribeProcessorUP_FIVE extends BaseSubscribeProcessor {

  @Override
  protected String getTopicType() {
    return "消息订阅";
  }

  @Override
  protected boolean processTopicSpecificSubscribe(MQTTUPRequest request) {
    return true;
  }
}
