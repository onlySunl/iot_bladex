package org.springblade.modules.iot.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;

public interface IDeviceShadowService extends BladeService<DeviceShadow> {
	DeviceShadow getByDeviceId(Long deviceId);
}
