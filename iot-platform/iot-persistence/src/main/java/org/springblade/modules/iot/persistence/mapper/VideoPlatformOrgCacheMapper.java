/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.persistence.common.BaseMapper;
import org.springblade.modules.iot.pojo.entity.VideoPlatformOrgCache;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoPlatformOrgCacheMapper extends BaseMapper<VideoPlatformOrgCache> {
  List<VideoPlatformOrgCache> selectByInstanceKey(@Param("instanceKey") String instanceKey);
  VideoPlatformOrgCache selectOneByInstanceAndOrg(
      @Param("instanceKey") String instanceKey, @Param("orgId") String orgId);
}
