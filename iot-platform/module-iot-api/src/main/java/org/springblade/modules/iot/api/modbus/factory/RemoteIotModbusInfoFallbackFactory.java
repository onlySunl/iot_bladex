package org.springblade.modules.iot.api.modbus.factory;

import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.api.modbus.service.RemoteIotModbusInfoService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteIotModbusInfoFallbackFactory implements FallbackFactory<RemoteIotModbusInfoService> {
    @Override
    public RemoteIotModbusInfoService create(Throwable cause) {
        return new RemoteIotModbusInfoService() {
            @Override
            public ModbusInfo findByProductKey(String productKey) {
                // 熔断返回空对象，可自定义异常抛出
                return null;
            }
        };
    }
}