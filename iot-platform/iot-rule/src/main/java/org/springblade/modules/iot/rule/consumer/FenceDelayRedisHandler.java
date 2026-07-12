package org.springblade.modules.iot.rule.consumer;

import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.config.InstanceIdProvider;
import org.springblade.modules.iot.common.event.EventMessage;
import org.springblade.modules.iot.common.event.processer.FenceEventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 电子围栏延迟事件Redis处理器 替代FenceDelayConsumerConfigure */
@Slf4j
@Component
public class FenceDelayRedisHandler implements FenceEventProcessor {

  @Autowired private InstanceIdProvider instanceIdProvider;

  @Autowired private FenceDelayConsumer fenceDelayConsumer;

  /** 处理电子围栏延迟事件 */
  @Override
  public void handleFenceEvent(EventMessage message) {
    try {
      // 检查是否为自己的消息
      if (isOwnMessage(message)) {
        log.debug("跳过自己发出的围栏延迟消息");
        return;
      }

      log.info("收到电子围栏延迟事件: {}", message);
      processFenceDelay(message);

    } catch (Exception e) {
      log.error("处理围栏延迟事件失败: message={}, error={}", message, e.getMessage(), e);
    }
  }

  /** 处理围栏延迟逻辑 */
  private void processFenceDelay(EventMessage message) {
    try {
      log.info("处理围栏延迟消息: {}", message);
      fenceDelayConsumer.consumer(JSONUtil.toJsonStr(message.getData()));
    } catch (Exception e) {
      log.error("处理围栏延迟消息失败: {}", e.getMessage(), e);
    }
  }

  /** 判断是否是自己发送的消息 */
  private boolean isOwnMessage(EventMessage message) {
    try {
      String nodeId = message.getNodeId();
      return instanceIdProvider.getInstanceId().equals(nodeId);
    } catch (Exception e) {
      return false;
    }
  }
}
