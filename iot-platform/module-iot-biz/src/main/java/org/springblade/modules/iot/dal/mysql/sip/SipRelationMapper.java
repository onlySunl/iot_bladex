

package org.springblade.modules.iot.dal.mysql.sip;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.mybatis.core.query.MPJLambdaWrapperX;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelationPageReqVO;
import org.springblade.modules.iot.entity.DeviceChannelDO;
import org.springblade.modules.iot.entity.SipRelationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 监控设备关联 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface SipRelationMapper extends BaseMapperX<SipRelationDO> {

    default List<SipRelation> selectSipRelationList(SipRelation sipRelation) {
        MPJLambdaWrapperX<SipRelationDO> query = new MPJLambdaWrapperX<SipRelationDO>()
                .selectAll(SipRelationDO.class)
                .selectAs(SipRelation::getStatus, DeviceChannelDO::getStatus)
                .selectAs(SipRelation::getDeviceSipId, DeviceChannelDO::getDeviceSipId)
                .selectAs(SipRelation::getChannelName, DeviceChannelDO::getChannelName)
                .selectAs(SipRelation::getModel, DeviceChannelDO::getModel)
                .eqIfPresent(SipRelationDO::getChannelId, sipRelation.getChannelId())
                .eqIfPresent(SipRelationDO::getReDeviceId, sipRelation.getDeviceSipId())
                .eqIfPresent(SipRelationDO::getReSceneModelId, sipRelation.getReSceneModelId());
        query.leftJoin(DeviceChannelDO.class, DeviceChannelDO::getChannelSipId, SipRelationDO::getChannelId);
        return selectJoinList(SipRelation.class, query);


    }

    default PageResult<SipRelation> selectPage(SipRelationPageReqVO reqVO) {
        MPJLambdaWrapperX<SipRelationDO> query = new MPJLambdaWrapperX<SipRelationDO>()
                .selectAll(SipRelationDO.class)
                .selectAs(SipRelation::getStatus, DeviceChannelDO::getStatus)
                .selectAs(SipRelation::getDeviceSipId, DeviceChannelDO::getDeviceSipId)
                .selectAs(SipRelation::getChannelName, DeviceChannelDO::getChannelName)
                .selectAs(SipRelation::getModel, DeviceChannelDO::getModel)
                .eqIfPresent(SipRelationDO::getChannelId, reqVO.getChannelId())
                .eqIfPresent(SipRelationDO::getReDeviceId, reqVO.getReDeviceId())
                .eqIfPresent(SipRelationDO::getReSceneModelId, reqVO.getReSceneModelId());
        query.leftJoin(DeviceChannelDO.class, DeviceChannelDO::getChannelSipId, SipRelationDO::getChannelId);
        return selectJoinPage(reqVO,SipRelation.class, query);
    }

    default SipRelationDO selectByChannelId(String channelId){
        return selectOne(SipRelationDO::getChannelId, channelId);
    }

    default int updateByChannelId(SipRelation sipRelation){
        LambdaUpdateWrapper<SipRelationDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SipRelationDO::getReDeviceId, sipRelation.getReDeviceId())
                .set(SipRelationDO::getReSceneModelId, sipRelation.getReSceneModelId())
                .eq(SipRelationDO::getId, sipRelation.getId());

        return update(null, updateWrapper);
    }
}
