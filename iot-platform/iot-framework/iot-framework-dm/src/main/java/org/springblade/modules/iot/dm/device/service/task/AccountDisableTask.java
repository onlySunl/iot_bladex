

package org.springblade.modules.iot.dm.device.service.task;

import org.springblade.modules.iot.persistence.mapper.IoTUserMapper;
import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountDisableTask {

  private final String KEY = "job:accountDisable";
  @Resource private StringRedisTemplate stringRedisTemplate;
  @Resource private IoTUserMapper iotUserMapper;

  @Scheduled(cron = "0 20 1 * *  ? ")
  public void doAccountDisable() {
    // 分布式锁,只执行1次，必须设置失效时间
    boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(KEY, "0", 5, TimeUnit.MINUTES);
    if (!flag) {
      return;
    }
    log.info("开始执行账号禁用扫描");
    int rows = iotUserMapper.doAccountDisable();
    log.info("结束账号禁用扫描，本次禁用{}个账号", rows);
  }
}
