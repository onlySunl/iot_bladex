package org.springblade.modules.iot.api.modbus;

import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;

public interface ModbusThingModelApi {
    ModbusThingModel findByProductKey(String productKey);

    ModbusThingModel save(ModbusThingModel thingModel);
}
