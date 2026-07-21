package org.springblade.modules.iot.api.modbus.service;


import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springblade.modules.iot.api.modbus.factory.RemoteIotModbusThingModelFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Modbus物模型远程Feign调用
 */
@FeignClient(contextId = "remoteIotModbusThingModelService",
        value = IotServiceNameConstants.IOT_MODBUS_THING_MODEL,
        fallbackFactory = RemoteIotModbusThingModelFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotModbusThingModelService {

    @GetMapping("/api/modbusThingModelApi/findByProductKey")
    ModbusThingModel findByProductKey(@RequestParam("productKey") String productKey);

    @PostMapping("/api/modbusThingModelApi/save")
    ModbusThingModel save(@RequestBody ModbusThingModel thingModel);
}