package org.springblade.modules.iot.api.modbus.service;

import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.api.modbus.factory.RemoteIotModbusInfoFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Modbus信息远程Feign调用
 */
@FeignClient(contextId = "remoteIotModbusInfoService",
        value = IotServiceNameConstants.IOT_MODBUS_INFO,
        fallbackFactory = RemoteIotModbusInfoFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotModbusInfoService {

    @GetMapping("/modbusInfo/findByProductKey")
    ModbusInfo findByProductKey(@RequestParam("productKey") String productKey);
}