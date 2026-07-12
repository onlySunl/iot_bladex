package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.DeviceGroup;

/**
 * IoT设备分组 Mapper 接口
 */
@Mapper
public interface DeviceGroupMapper extends BladeMapper<DeviceGroup> {
}
