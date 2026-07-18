package org.springblade.modules.iot.convert;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.entity.ModbusInfoDO;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ModbusInfoConvert {
    ModbusInfoConvert INSTANCE = Mappers.getMapper(ModbusInfoConvert.class);

    ModbusInfo convert(ModbusInfoDO modbusInfoDO);

    PageResult<ModbusInfo> convertPage(PageResult<ModbusInfoDO> selectPage);
}
