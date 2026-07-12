package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;

public interface IDeviceShadowService extends IService<DeviceShadow> {
	DeviceShadow getByDeviceId(Long deviceId);
}
