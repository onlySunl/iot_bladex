package org.springblade.modules.iot.dal.mysql.deviceconfig;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.DeviceConfigDO;

/**
 * 设备配置 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface DeviceConfigMapper extends BladeMapper<DeviceConfigDO> {

    DeviceConfigDO selectByPkDn(@Param("productKey") String productKey, @Param("dn") String dn);
}
