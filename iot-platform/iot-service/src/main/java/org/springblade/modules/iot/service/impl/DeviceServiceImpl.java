package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.modules.iot.mapper.DeviceMapper;
import org.springblade.modules.iot.pojo.entity.Device;
import org.springblade.modules.iot.service.IDeviceService;
import org.springframework.stereotype.Service;

/**
 * IoT设备 服务实现类
 */
@Service
public class DeviceServiceImpl extends BladeServiceImpl<DeviceMapper, Device> implements IDeviceService {
}
