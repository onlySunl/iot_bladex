/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 视频平台实例Mapper
 * @Author: gitee.com/NexIoT
 *
 */
package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.VideoPlatformInstance;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoPlatformInstanceMapper extends BladeMapper<VideoPlatformInstance> {
  List<VideoPlatformInstance> selectByPlatformType(@Param("platformType") String platformType);
}
