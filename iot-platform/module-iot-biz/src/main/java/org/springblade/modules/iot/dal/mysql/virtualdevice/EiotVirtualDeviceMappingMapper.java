

package org.springblade.modules.iot.dal.mysql.virtualdevice;

import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.VirtualDeviceMappingDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规则引擎 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotVirtualDeviceMappingMapper extends BaseMapperX<VirtualDeviceMappingDO> {


    default List<VirtualDeviceMappingDO> findByVirtualDeviceId(Long virtualDeviceId) {
        return selectList(new LambdaQueryWrapperX<VirtualDeviceMappingDO>()
                .eqIfPresent(VirtualDeviceMappingDO::getVirtualDeviceId, virtualDeviceId));
    }

    default int deleteByVirtualDeviceId(Long virtualDeviceId) {
        LambdaQueryWrapperX<VirtualDeviceMappingDO> queryWrapperX = new LambdaQueryWrapperX<VirtualDeviceMappingDO>()
                .eq(VirtualDeviceMappingDO::getVirtualDeviceId, virtualDeviceId);
        return delete(queryWrapperX);
    }

}
