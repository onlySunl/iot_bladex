

package org.springblade.modules.iot.dm.device.service.impl;

import org.springblade.modules.iot.core.protocol.support.ProtocolSupportDefinition;
import org.springblade.modules.iot.core.service.AbstractCodecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 统一编解码服务实现类
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/01/20
 */
@Slf4j
@Service
public class UniversalCodecServiceImpl extends AbstractCodecService {

  @Autowired private IoTProductDeviceService iotProductDeviceService;

  @Override
  protected ProtocolSupportDefinition getProtocolDefinition(String productKey) {
    return iotProductDeviceService.selectProtocolDef(productKey);
  }

  @Override
  protected ProtocolSupportDefinition getProtocolDefinitionNoScript(String productKey) {
    return iotProductDeviceService.selectProtocolDefNoScript(productKey);
  }

  @Override
  protected ProtocolSupportDefinition getProtocolDefinitionWithScript(String productKey) {
    return iotProductDeviceService.selectProtocolDef(productKey);
  }
}
