package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.DeviceTagsMapper;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import org.springblade.modules.iot.service.IDeviceTagsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class DeviceTagsServiceImpl extends ServiceImpl<DeviceTagsMapper, DeviceTags> implements IDeviceTagsService {

	@Override
	public List<DeviceTags> getByDeviceId(Long deviceId) {
		LambdaQueryWrapper<DeviceTags> qw = new LambdaQueryWrapper<>();
		qw.eq(DeviceTags::getDeviceId, deviceId);
		qw.eq(DeviceTags::getIsDeleted, 0);
		return list(qw);
	}
}
