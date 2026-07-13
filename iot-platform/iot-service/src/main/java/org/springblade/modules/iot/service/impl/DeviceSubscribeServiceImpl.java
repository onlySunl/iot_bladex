package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.DeviceSubscribeMapper;
import org.springblade.modules.iot.pojo.entity.DeviceSubscribe;
import org.springblade.modules.iot.service.IDeviceSubscribeService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceSubscribeServiceImpl extends BladeServiceImpl<DeviceSubscribeMapper, DeviceSubscribe> implements IDeviceSubscribeService {
}
