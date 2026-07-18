

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.entity.ChannelConfigDO;
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
public interface ChannelConfigConvert {
    ChannelConfigConvert INSTANCE = Mappers.getMapper(ChannelConfigConvert.class);

    ChannelConfig convert(ChannelConfigDO channelConfigDO);

    PageResult<ChannelConfig> convertPage(PageResult<ChannelConfigDO> selectPage);

    List<ChannelConfig> convertList(List<ChannelConfigDO> selectList);
}
