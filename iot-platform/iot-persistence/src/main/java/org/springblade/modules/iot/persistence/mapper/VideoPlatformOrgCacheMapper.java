/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.VideoPlatformOrgCache;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoPlatformOrgCacheMapper extends BladeMapper<VideoPlatformOrgCache> {
  List<VideoPlatformOrgCache> selectByInstanceKey(@Param("instanceKey") String instanceKey);
  VideoPlatformOrgCache selectOneByInstanceAndOrg(
      @Param("instanceKey") String instanceKey, @Param("orgId") String orgId);
}
