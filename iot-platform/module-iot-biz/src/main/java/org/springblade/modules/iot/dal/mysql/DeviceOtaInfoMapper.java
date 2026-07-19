

package org.springblade.modules.iot.dal.mysql;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.ota.vo.DeviceOtaPageReq;
import org.springblade.modules.iot.entity.DeviceOtaInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

/**
 * 设备ota信息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface DeviceOtaInfoMapper extends BaseMapperX<DeviceOtaInfoDO> {

    default PageResult<DeviceOtaInfoDO> selectPage(DeviceOtaPageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceOtaInfoDO>()
                .eqIfPresent(DeviceOtaInfoDO::getPackageId, reqVO.getPackageId())
                .orderByDesc(DeviceOtaInfoDO::getId));
    }

}
