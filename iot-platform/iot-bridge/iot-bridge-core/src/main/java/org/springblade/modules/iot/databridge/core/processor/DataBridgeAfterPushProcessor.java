

package org.springblade.modules.iot.databridge.core.processor;

import cn.hutool.core.collection.CollectionUtil;
import org.springblade.modules.iot.databridge.core.manager.DataBridgeManager;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据桥接推送后处理器 在推送后执行数据桥接
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Component
@Slf4j
public class DataBridgeAfterPushProcessor {

  @Resource private DataBridgeManager dataBridgeManager;

  @Value("${databridge.enabled:false}")
  private boolean dataBridgeEnabled;

  /** 在推送后执行数据桥接 */
  public void executeAfterPush(List<BaseUPRequest> upRequests) {
    if (CollectionUtil.isEmpty(upRequests) || !dataBridgeEnabled) {
      return;
    }

    log.debug("[数据桥接] 开始处理 {} 条消息", upRequests.size());

    try {
      // 异步执行数据桥接
      dataBridgeManager.processDeviceData(upRequests);
    } catch (Exception e) {
      log.error("[数据桥接] 处理异常: {}", e.getMessage(), e);
    }
  }
}
