

package org.springblade.modules.iot.dal.mysql;

import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.DeviceGroupDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 设备分组 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotIotDeviceGroupMapper extends BaseMapperX<DeviceGroupDO> {


    default void deleteByDeviceIds(List<Long> deviceIds){
        LambdaQueryWrapperX<DeviceGroupDO> queryWrapperX = new LambdaQueryWrapperX<DeviceGroupDO>().in(DeviceGroupDO::getDeviceId, deviceIds);
        delete(queryWrapperX);
    };

    default int removeDevicesInGroup(Long groupId,List<Long> deviceIds){
        LambdaQueryWrapperX<DeviceGroupDO> queryWrapperX = new LambdaQueryWrapperX<DeviceGroupDO>()
                .eq(DeviceGroupDO::getGroupId, groupId)
                .in(DeviceGroupDO::getDeviceId, deviceIds);
        return delete(queryWrapperX);
    };
}
