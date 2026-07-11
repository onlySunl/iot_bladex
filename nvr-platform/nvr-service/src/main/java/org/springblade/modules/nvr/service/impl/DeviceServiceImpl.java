package org.springblade.modules.nvr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.nvr.mapper.DeviceMapper;
import org.springblade.modules.nvr.pojo.entity.Device;
import org.springblade.modules.nvr.service.IDeviceService;
import org.springframework.stereotype.Service;

/**
 * IoT设备 服务实现类
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {
}
