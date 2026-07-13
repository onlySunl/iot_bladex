package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.modules.iot.mapper.DeviceGroupMapper;
import org.springblade.modules.iot.pojo.entity.DeviceGroup;
import org.springblade.modules.iot.service.IDeviceGroupService;
import org.springframework.stereotype.Service;

/**
 * IoT设备分组 服务实现类
 */
@Service
public class DeviceGroupServiceImpl extends BladeServiceImpl<DeviceGroupMapper, DeviceGroup> implements IDeviceGroupService {
}
