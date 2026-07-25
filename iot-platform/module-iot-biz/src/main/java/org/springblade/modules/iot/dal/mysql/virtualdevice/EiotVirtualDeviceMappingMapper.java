package org.springblade.modules.iot.dal.mysql.virtualdevice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.VirtualDeviceMappingDO;

import java.util.List;

/**
 * 虚拟设备映射 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotVirtualDeviceMappingMapper extends BladeMapper<VirtualDeviceMappingDO> {

    List<VirtualDeviceMappingDO> findByVirtualDeviceId(@Param("virtualDeviceId") Long virtualDeviceId);

    int deleteByVirtualDeviceId(@Param("virtualDeviceId") Long virtualDeviceId);
}
