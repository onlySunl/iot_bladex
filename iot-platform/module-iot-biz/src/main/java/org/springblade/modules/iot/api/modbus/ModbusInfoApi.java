package org.springblade.modules.iot.api.modbus;

import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;

public interface ModbusInfoApi {
    ModbusInfo findByProductKey(String productKey);
}
