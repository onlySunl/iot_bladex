

package org.springblade.modules.iot.temporal.es.convert;


import org.springblade.modules.iot.temporal.es.document.DocDeviceProperty;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
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

public interface EsDevicePropertyConvert {
    EsDevicePropertyConvert INSTANCE = Mappers.getMapper(EsDevicePropertyConvert.class);

    DeviceProperty convert(DocDeviceProperty content);
}
