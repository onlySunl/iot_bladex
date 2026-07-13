

package org.springblade.modules.iot.rule.scene.deviceUp;

import org.springblade.modules.iot.common.constant.IoTConstant;
import org.springblade.modules.iot.common.message.UPRequest;
import org.springblade.modules.iot.pojo.bo.TriggerBO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceEventUp extends AbstractDeviceUp implements DeviceUp {

  @Override
  public String messageType() {
    return IoTConstant.MessageType.EVENT.name();
  }

  @Override
  public boolean testAlarm(List<TriggerBO> triggers, String separator, UPRequest upRequest) {
    String express =
        triggers.stream()
            .map(triggerBo -> String.format("'%s'== event", triggerBo.getModelId()))
            .collect(Collectors.joining(separator));
    Map<String, Object> content = new HashMap<>(2);
    content.put("event", upRequest.getEvent());
    content.put("eventName", upRequest.getEventName());
    content.put("eventData", upRequest.getData());
    log.info("执行场景联动条件,express={},properties={}", express, content);
    return expressTemplate.executeTest(express, content);
  }
}
