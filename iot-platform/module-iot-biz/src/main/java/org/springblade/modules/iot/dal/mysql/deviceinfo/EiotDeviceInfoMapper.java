

package org.springblade.modules.iot.dal.mysql.deviceinfo;

import cn.hutool.core.util.ObjectUtil;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.mybatis.core.query.MPJLambdaWrapperX;
import org.springblade.modules.iot.api.device.dto.DeviceShortInfo;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceInfoPageReqVO;
import org.springblade.modules.iot.entity.DeviceGroupDO;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;
import org.springblade.modules.iot.entity.ProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备信息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotDeviceInfoMapper extends BaseMapperX<EiotDeviceInfoDO> {

    default PageResult<DeviceShortInfo> selectPage(DeviceInfoPageReqVO reqVO) {
        MPJLambdaWrapperX<EiotDeviceInfoDO> q = new MPJLambdaWrapperX<EiotDeviceInfoDO>()
                .selectAll(EiotDeviceInfoDO.class)
                .selectAs(ProductDO::getName, DeviceShortInfo::getProductName)
                .likeIfPresent(EiotDeviceInfoDO::getDn, reqVO.getDn())
                .eqIfPresent(EiotDeviceInfoDO::getProductKey, reqVO.getProductKey())
                .inIfPresent(EiotDeviceInfoDO::getProductKey, reqVO.getProductKeyList())
                .eqIfPresent(EiotDeviceInfoDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(EiotDeviceInfoDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(EiotDeviceInfoDO::getName, reqVO.getName())
                .eqIfPresent(EiotDeviceInfoDO::getState, reqVO.getState())
                .eqIfPresent(EiotDeviceInfoDO::getSerialNo, reqVO.getSerialNo())
                .eqIfPresent(EiotDeviceInfoDO::getParentId, reqVO.getParentId())
                .isNull(reqVO.getBindStatus() != null && !reqVO.getBindStatus(), EiotDeviceInfoDO::getParentId)
                .orderByDesc(EiotDeviceInfoDO::getId);
        q.leftJoin(ProductDO.class, ProductDO::getProductKey, EiotDeviceInfoDO::getProductKey);
        if(ObjectUtil.isNotNull(reqVO.getGroupId())){
            q.innerJoin(DeviceGroupDO.class, DeviceGroupDO::getDeviceId, EiotDeviceInfoDO::getId);
            q.eq(DeviceGroupDO::getGroupId, reqVO.getGroupId());
        }
        q.eq(ObjectUtil.isNotNull(reqVO.getNodeType()), ProductDO::getNodeType, reqVO.getNodeType());
        return selectJoinPage(reqVO, DeviceShortInfo.class, q);
    }

    default Long selectCountByProductKey(String productKey){
        return selectCount(EiotDeviceInfoDO::getProductKey, productKey);
    }
}
