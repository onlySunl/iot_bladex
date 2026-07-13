

package org.springblade.modules.iot.rocketmq;

import java.util.HashMap;
import java.util.Set;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/6/29
 */
public interface RocketMQMonitor {

  /**
   * 检查消费组信息
   *
   * @param defaultTopic
   * @return
   */
  HashMap<String, Set<String>> queryDefaultTopicExistConsumer(String defaultTopic);
}
