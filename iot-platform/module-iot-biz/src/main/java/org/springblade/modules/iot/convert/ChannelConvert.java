

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.Channel;
import org.springblade.modules.iot.entity.ChannelDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */


@Mapper(builder = @Builder(disableBuilder = true))

public interface ChannelConvert {
    ChannelConvert INSTANCE = Mappers.getMapper(ChannelConvert.class);

    Channel convert(ChannelDO channelConfigDO);

    PageResult<Channel> convertPage(PageResult<ChannelDO> selectPage);

    List<Channel> convertList(List<ChannelDO> selectList);
}
