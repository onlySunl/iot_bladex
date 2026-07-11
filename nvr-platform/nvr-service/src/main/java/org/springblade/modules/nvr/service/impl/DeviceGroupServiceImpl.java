package org.springblade.modules.nvr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.nvr.mapper.DeviceGroupMapper;
import org.springblade.modules.nvr.pojo.entity.DeviceGroup;
import org.springblade.modules.nvr.service.IDeviceGroupService;
import org.springframework.stereotype.Service;

/**
 * IoT设备分组 服务实现类
 */
@Service
public class DeviceGroupServiceImpl extends ServiceImpl<DeviceGroupMapper, DeviceGroup> implements IDeviceGroupService {
}
