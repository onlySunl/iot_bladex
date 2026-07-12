package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.DeviceSubscribeMapper;
import org.springblade.modules.iot.pojo.entity.DeviceSubscribe;
import org.springblade.modules.iot.service.IDeviceSubscribeService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceSubscribeServiceImpl extends ServiceImpl<DeviceSubscribeMapper, DeviceSubscribe> implements IDeviceSubscribeService {
}
