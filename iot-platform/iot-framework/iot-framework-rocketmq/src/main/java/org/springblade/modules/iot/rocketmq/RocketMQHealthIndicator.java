

package org.springblade.modules.iot.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/** RocketMQ 健康检查 @Author gitee.com/NexIoT */
@Slf4j
@Component
public class RocketMQHealthIndicator implements HealthIndicator {
  @Autowired(required = false)
  private RocketMQService rocketMQService;

  @Override
  public Health health() {
    if (rocketMQService == null) {
      return Health.down().withDetail("status", "RocketMQ connection failed").build();
    }
    try {
      boolean isHealthy = rocketMQService.rocketmqNormal();
      if (isHealthy) {
        return Health.up().withDetail("status", "RocketMQ is healthy").build();
      } else {
        return Health.down().withDetail("status", "RocketMQ connection failed").build();
      }
    } catch (Exception e) {
      log.error("RocketMQ health check failed", e);
      return Health.down().withDetail("error", e.getMessage()).build();
    }
  }
}
