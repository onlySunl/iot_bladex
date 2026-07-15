package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.dal.dataobject.modbus.ModbusInfoDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ModbusInfoConvert {
    ModbusInfoConvert INSTANCE = Mappers.getMapper(ModbusInfoConvert.class);

    ModbusInfo convert(ModbusInfoDO modbusInfoDO);

    PageResult<ModbusInfo> convertPage(PageResult<ModbusInfoDO> selectPage);
}
