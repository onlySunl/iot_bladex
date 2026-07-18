

package org.springblade.modules.iot.convert;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.entity.AlertRecordDO;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */
@Mapper(builder = @Builder(disableBuilder = true))

public interface AlertRecordConvert {
    AlertRecordConvert INSTANCE = Mappers.getMapper(AlertRecordConvert.class);

    AlertRecord convert(AlertRecordDO channelConfigDO);

    PageResult<AlertRecord> convertPage(PageResult<AlertRecordDO> selectPage);

    AlertRecordDO convertDO(AlertRecord build);
}
