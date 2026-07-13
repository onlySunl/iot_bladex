package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.DeviceLogMapper;
import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.springblade.modules.iot.service.IDeviceLogService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceLogServiceImpl extends BladeServiceImpl<DeviceLogMapper, DeviceLog> implements IDeviceLogService {
}
