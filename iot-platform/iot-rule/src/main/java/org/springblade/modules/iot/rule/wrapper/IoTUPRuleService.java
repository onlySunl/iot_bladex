

package org.springblade.modules.iot.rule.wrapper;

import cn.hutool.core.collection.CollectionUtil;
import org.springblade.modules.iot.common.constant.IoTConstant.MessageType;
import org.springblade.modules.iot.dm.device.service.wrapper.IoTDeviceUPIntercept;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.base.IoTUPWrapper;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.rule.fence.service.FenceService;
import org.springblade.modules.iot.rule.scene.service.SceneLinkageService;
import org.springblade.modules.iot.rule.service.RuleService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/1/20
 */
@Service("iotUPRuleService")
public class IoTUPRuleService implements IoTUPWrapper<BaseUPRequest> {

  @Resource private SceneLinkageService sceneLinkageService;

  @Resource private RuleService ruleService;

  @Resource private FenceService fenceService;

  @Resource private IoTDeviceUPIntercept iotDeviceUPWrapper;

  @Override
  //  @Async
  public void beforePush(List<BaseUPRequest> baseUPRequests) {
    if (CollectionUtil.isEmpty(baseUPRequests)) {
      return;
    }
    baseUPRequests.stream()
        .filter(s -> s != null)
        .forEach(
            baseUPRequest -> {
              IoTDeviceDTO dev = baseUPRequest.getIoTDeviceDTO();
              if (dev == null) {
                return;
              }
              // 调用规则引擎
              ruleService.rule(baseUPRequest, dev);
              // 调用场景联动
              sceneLinkageService.rule(baseUPRequest, dev);
              /*
               * 上行消息，属性和事件上报的后置处理，包括
               */
              iotDeviceUPWrapper.messageProcess(baseUPRequest);

              if (dev != null && dev.getProductConfig() != null) {
                if (dev.getProductConfig().containsKey("isGps")
                    && dev.getProductConfig().getBool("isGps")
                    && baseUPRequest.getMessageType().equals(MessageType.PROPERTIES)) {
                  fenceService.testFence(baseUPRequest, dev);
                }
              }
            });
  }
}
