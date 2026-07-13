

package org.springblade.modules.iot.rule.scene.deviceUp;
import org.springblade.modules.iot.common.enums.Operator;
import org.springblade.modules.iot.common.enums.MessageType;
import Operator;
import MessageType;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.common.constant.IoTConstant;
import org.springblade.modules.iot.common.message.UPRequest;
import org.springblade.modules.iot.pojo.bo.TriggerBO;
import org.springblade.modules.iot.pojo.bo.Operator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DevicePropertiesUp extends AbstractDeviceUp implements DeviceUp {

  @Override
  public String messageType() {
    return MessageType.PROPERTIES.name();
  }

  @Override
  public boolean testAlarm(List<TriggerBO> triggers, String separator, UPRequest upRequest) {
    Map<String, Object> properties = upRequest.getProperties();
    String express =
        triggers.stream()
            .map(
                triggerBo -> {
                  String filterExpress =
                      triggerBo.getFilters().stream()
                          .filter(item -> properties.containsKey(item.getKey()))
                          .map(
                              filter ->
                                  String.format(
                                      "%s %s %s",
                                      filter.getKey(),
                                      Operator.valueOf(filter.getOperator()).getSymbol(),
                                      NumberUtil.isNumber(filter.getValue())
                                          ? filter.getValue()
                                          : String.format("'%s'", filter.getValue())))
                          .collect(Collectors.joining(separator));
                  return StrUtil.isEmpty(filterExpress) ? "" : String.format("(%s)", filterExpress);
                })
            .filter(StrUtil::isNotEmpty)
            .collect(Collectors.joining(separator));
    if (StrUtil.isEmpty(express)) {
      return false;
    }
    log.info("执行场景联动条件,express={},properties={}", express, properties);
    return expressTemplate.executeTest(express, properties);
  }
}
