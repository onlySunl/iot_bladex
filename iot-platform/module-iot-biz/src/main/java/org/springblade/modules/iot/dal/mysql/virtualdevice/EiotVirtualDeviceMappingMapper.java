

package org.springblade.modules.iot.dal.mysql.virtualdevice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.VirtualDeviceMappingDO;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

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
        LambdaQueryWrapper<VirtualDeviceMappingDO> queryWrapperX = new LambdaQueryWrapperX<VirtualDeviceMappingDO>()
                .eq(VirtualDeviceMappingDO::getVirtualDeviceId, virtualDeviceId);
        return delete(queryWrapperX);
    }

}
