package org.springblade.modules.iot.api.modbus.factory;


import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springblade.modules.iot.api.modbus.service.RemoteIotModbusThingModelService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteIotModbusThingModelFallbackFactory implements FallbackFactory<RemoteIotModbusThingModelService> {
    @Override
    public RemoteIotModbusThingModelService create(Throwable cause) {
        return new RemoteIotModbusThingModelService() {
            @Override
            public ModbusThingModel findByProductKey(String productKey) {
                return new  ModbusThingModel();
            }

            @Override
            public ModbusThingModel save(ModbusThingModel thingModel) {
                return new ModbusThingModel();
            }
        };
    }
}