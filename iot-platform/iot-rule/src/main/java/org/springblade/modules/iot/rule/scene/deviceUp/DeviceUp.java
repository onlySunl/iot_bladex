

package org.springblade.modules.iot.rule.scene.deviceUp;

import org.springblade.modules.iot.common.message.UPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;

public interface DeviceUp {

  String messageType();

  void consumer(UPRequest upRequest, IoTDeviceDTO ioTDeviceDTO);
}
