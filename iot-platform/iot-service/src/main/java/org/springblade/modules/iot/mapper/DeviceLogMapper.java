package org.springblade.modules.iot.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceLogMapper extends BladeMapper<DeviceLog> {
}
