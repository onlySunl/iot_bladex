

package org.springblade.modules.iot.protocol.mqtt.processor.up.passthrough;

import org.springblade.modules.iot.protocol.mqtt.config.MqttConstant;
import org.springblade.modules.iot.protocol.mqtt.config.MqttConstant.TopicCategory;
import org.springblade.modules.iot.pojo.protocol.mqtt.MQTTUPRequest;
import org.springblade.modules.iot.protocol.mqtt.processor.up.common.BaseDeviceInfoProcessor;
import org.springblade.modules.iot.protocol.mqtt.topic.MQTTTopicManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 透传主题设备信息处理器
 *
 * <p>处理透传主题的设备信息提取和回填： - $thing/up/${productKey}/${deviceId} (透传上行) -
 * $thing/down/${productKey}/${deviceId} (透传下行)
 *
 * <p>透传特点： - 消息格式不固定，需要编解码 - 支持各种自定义协议格式 - 原始数据需要通过编解码器转换为物模型
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/20
 */
@Slf4j(topic = "mqtt")
@Component
public class PassthroughDeviceInfoProcessor extends BaseDeviceInfoProcessor {

  @Autowired private MQTTTopicManager topicManager;

  @Override
  protected String getTopicType() {
    return "透传";
  }

  @Override
  public boolean supports(MQTTUPRequest request) {
    if (request.getUpTopic() == null
        || request.getPayload() == null
        || !request.isSysMQTTBroker()) {
      return false;
    }
    // 检查是否为透传主题
    TopicCategory topicCategory = request.getTopicCategory();
    if (topicCategory != null) {
      return MqttConstant.TopicCategory.PASSTHROUGH.equals(topicCategory);
    }
    topicCategory = MQTTTopicManager.matchCategory(request.getUpTopic());
    request.setTopicCategory(topicCategory);
    return MqttConstant.TopicCategory.PASSTHROUGH.equals(topicCategory);
  }

  @Override
  public int getPriority() {
    return 5; // 透传处理优先级中等
  }
}
