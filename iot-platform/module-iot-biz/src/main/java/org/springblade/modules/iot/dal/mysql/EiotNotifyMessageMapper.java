

package org.springblade.modules.iot.dal.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.alert.vo.NotifyMessagePageReq;
import org.springblade.modules.iot.entity.IotNotifyMessageDO;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

/**
 * iot通知消息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotNotifyMessageMapper extends BaseMapperX<IotNotifyMessageDO> {

    default PageResult<IotNotifyMessageDO> selectPage(NotifyMessagePageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IotNotifyMessageDO>()
                .eqIfPresent(IotNotifyMessageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IotNotifyMessageDO::getMessageType, reqVO.getMessageType())
                .orderByDesc(IotNotifyMessageDO::getId));
    }

}
