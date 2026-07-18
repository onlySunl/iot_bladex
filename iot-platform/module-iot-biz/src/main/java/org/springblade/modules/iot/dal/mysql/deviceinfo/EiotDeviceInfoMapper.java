package org.springblade.modules.iot.dal.mysql.deviceinfo;

import cn.hutool.core.util.ObjectUtil;
import org.springblade.modules.iot.common.entity.PageResult;
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
                .eqIfPresent(EiotDeviceInfoDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(EiotDeviceInfoDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(EiotDeviceInfoDO::getName, reqVO.getName())
                .eqIfPresent(EiotDeviceInfoDO::getState, reqVO.getState())
                .eqIfPresent(EiotDeviceInfoDO::getSerialNo, reqVO.getSerialNo())
                .eqIfPresent(EiotDeviceInfoDO::getParentId, reqVO.getParentId())
                .orderByDesc(EiotDeviceInfoDO::getId);

        // 产品key 单值/列表互斥处理
        if (ObjectUtil.isNotEmpty(reqVO.getProductKeyList())) {
            q.in(EiotDeviceInfoDO::getProductKey, reqVO.getProductKeyList());
        } else {
            q.eqIfPresent(EiotDeviceInfoDO::getProductKey, reqVO.getProductKey());
        }

        // 绑定状态完整分支
        if (reqVO.getBindStatus() != null) {
            if (!reqVO.getBindStatus()) {
                q.isNull(EiotDeviceInfoDO::getParentId);
            } else {
                q.isNotNull(EiotDeviceInfoDO::getParentId);
            }
        }

        // 左连接产品表
        q.leftJoin(ProductDO.class, ProductDO::getProductKey, EiotDeviceInfoDO::getProductKey);
        // 节点类型过滤（左连接转内连接，按需确认业务）
        q.eq(ObjectUtil.isNotNull(reqVO.getNodeType()), ProductDO::getNodeType, reqVO.getNodeType());

        // 分组筛选
        if (ObjectUtil.isNotNull(reqVO.getGroupId())) {
            q.innerJoin(DeviceGroupDO.class, DeviceGroupDO::getDeviceId, EiotDeviceInfoDO::getId);
            q.eq(DeviceGroupDO::getGroupId, reqVO.getGroupId());
        }

        return selectJoinPage(reqVO, DeviceShortInfo.class, q);
    }

    /**
     * 根据产品productKey统计设备数量
     *
     * @param productKey 产品标识
     * @return 设备总数
     */
    default Long selectCountByProductKey(String productKey) {
        return selectCount(EiotDeviceInfoDO::getProductKey, productKey);
    }
}