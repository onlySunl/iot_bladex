

package org.springblade.modules.iot.service.device;


import org.springblade.modules.iot.api.device.dto.DeviceConfig;

public interface DeviceConfigService {

    DeviceConfig findByDeviceId(Long deviceId);

    /**
     * 按产品/设备标识获取配置
     */
    DeviceConfig findByPkDn(String productKey, String dn);

    /**
     * 保存/更新设备配置
     */
    Boolean saveConfig(DeviceConfig config);
}
