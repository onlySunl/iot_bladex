package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceLogMapper extends BaseMapper<DeviceLog> {
}
