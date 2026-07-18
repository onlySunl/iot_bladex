

package org.springblade.modules.iot.dal.mysql;

import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.ota.vo.DeviceOtaDetailPageReq;
import org.springblade.modules.iot.entity.OtaDetailDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备ota详情 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface OtaDetailMapper extends BaseMapperX<OtaDetailDO> {

    default PageResult<OtaDetailDO> selectPage(DeviceOtaDetailPageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OtaDetailDO>()
                .eqIfPresent(OtaDetailDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(OtaDetailDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(OtaDetailDO::getOtaInfoId, reqVO.getOtaInfoId())
                .eqIfPresent(OtaDetailDO::getProductKey, reqVO.getProductKey())
                .eqIfPresent(OtaDetailDO::getVersion, reqVO.getVersion())
                .orderByDesc(OtaDetailDO::getId));
    }

}
