

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.entity.ChannelTemplateDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface ChannelTemplateConvert {
    ChannelTemplateConvert INSTANCE = Mappers.getMapper(ChannelTemplateConvert.class);

    ChannelTemplate convert(ChannelTemplateDO templateDO);

    PageResult<ChannelTemplate> convertPage(PageResult<ChannelTemplateDO> selectPage);
}
