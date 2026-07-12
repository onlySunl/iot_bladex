/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.persistence.common.BaseMapper;
import org.springblade.modules.iot.persistence.entity.VideoPlatformDeviceCache;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoPlatformDeviceCacheMapper extends BaseMapper<VideoPlatformDeviceCache> {
  List<VideoPlatformDeviceCache> selectByInstanceKey(@Param("instanceKey") String instanceKey);
  VideoPlatformDeviceCache selectOneByInstanceAndDevice(
      @Param("instanceKey") String instanceKey, @Param("deviceId") String deviceId);
}
