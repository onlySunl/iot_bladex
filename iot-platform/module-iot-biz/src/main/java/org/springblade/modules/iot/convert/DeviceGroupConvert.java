

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.device.dto.DeviceGroup;
import org.springblade.modules.iot.entity.GroupDO;
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
public interface DeviceGroupConvert {
    DeviceGroupConvert INSTANCE = Mappers.getMapper(DeviceGroupConvert.class);

    DeviceGroup convert(GroupDO ylDeviceInfoDO);

    PageResult<DeviceGroup> convertPage(PageResult<GroupDO> selectPage);


    GroupDO convertDo(DeviceGroup g);
}
