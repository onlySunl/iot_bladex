

package org.springblade.modules.iot.convert;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.alert.vo.NotifyMessage;
import org.springblade.modules.iot.entity.IotNotifyMessageDO;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */


@Mapper(builder = @Builder(disableBuilder = true))

public interface NotifyMessageConvert {
    NotifyMessageConvert INSTANCE = Mappers.getMapper(NotifyMessageConvert.class);

    NotifyMessage convert(IotNotifyMessageDO channelConfigDO);

    PageResult<NotifyMessage> convertPage(PageResult<IotNotifyMessageDO> selectPage);
}
