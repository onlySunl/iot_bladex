package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.DeviceShadowMapper;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;
import org.springblade.modules.iot.service.IDeviceShadowService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceShadowServiceImpl extends ServiceImpl<DeviceShadowMapper, DeviceShadow> implements IDeviceShadowService {

	@Override
	public DeviceShadow getByDeviceId(Long deviceId) {
		LambdaQueryWrapper<DeviceShadow> qw = new LambdaQueryWrapper<>();
		qw.eq(DeviceShadow::getDeviceId, deviceId);
		qw.eq(DeviceShadow::getIsDeleted, 0);
		qw.last("limit 1");
		return getOne(qw);
	}
}
