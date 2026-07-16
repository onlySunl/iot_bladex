package org.springblade.modules.iot.service.modbus;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusInfoVo;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusThingModelImportVo;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusThingModelVo;

import jakarta.validation.Valid;
import java.util.List;

public interface ModbusInfoService {

    PageResult<ModbusInfo> selectPageList(@Valid ModbusInfoVo data);

    ModbusInfo createModbus(@Valid ModbusInfoVo data);

    boolean updateModbus(@Valid ModbusInfoVo data);

    ModbusInfo getModbus(Long data);

    boolean deleteModbus(Long data);

    ModbusThingModel getThingModelByProductKey(String data);

    boolean saveThingModel(@Valid ModbusThingModelVo data);

    boolean syncToProduct(@Valid ModbusThingModelVo data);

    String importData(List<ModbusThingModelImportVo> objects, String productKey);
}
