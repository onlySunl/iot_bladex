/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.VideoPlatformDeviceCache;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoPlatformDeviceCacheMapper extends BaseMapper<VideoPlatformDeviceCache> {
  List<VideoPlatformDeviceCache> selectByInstanceKey(@Param("instanceKey") String instanceKey);
  VideoPlatformDeviceCache selectOneByInstanceAndDevice(
      @Param("instanceKey") String instanceKey, @Param("deviceId") String deviceId);
}
