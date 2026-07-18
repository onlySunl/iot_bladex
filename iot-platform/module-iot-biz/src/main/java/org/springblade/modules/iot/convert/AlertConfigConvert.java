

package org.springblade.modules.iot.convert;


import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */

public interface AlertConfigConvert {
    AlertConfigConvert INSTANCE = Mappers.getMapper(AlertConfigConvert.class);

    AlertConfig convert(AlertConfigDO channelConfigDO);

    PageResult<AlertConfig> convertPage(PageResult<AlertConfigDO> selectPage);
}
