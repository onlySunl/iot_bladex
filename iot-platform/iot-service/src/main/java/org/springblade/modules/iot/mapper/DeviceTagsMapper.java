package org.springblade.modules.iot.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceTagsMapper extends BladeMapper<DeviceTags> {
}
