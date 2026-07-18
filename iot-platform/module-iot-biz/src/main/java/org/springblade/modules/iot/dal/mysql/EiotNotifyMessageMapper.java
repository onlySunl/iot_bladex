

package org.springblade.modules.iot.dal.mysql;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.controller.admin.alert.vo.NotifyMessagePageReq;
import org.springblade.modules.iot.entity.IotNotifyMessageDO;
import org.apache.ibatis.annotations.Mapper;

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
