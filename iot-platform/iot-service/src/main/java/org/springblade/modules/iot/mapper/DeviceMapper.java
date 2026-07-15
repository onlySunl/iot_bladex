package org.springblade.modules.iot.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.Device;

/**
 * IoT设备 Mapper 接口
 */
@Mapper
public interface DeviceMapper extends BladeMapper<Device> {
}
