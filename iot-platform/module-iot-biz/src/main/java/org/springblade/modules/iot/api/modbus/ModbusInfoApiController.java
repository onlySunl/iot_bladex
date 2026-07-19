package org.springblade.modules.iot.api.modbus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.modbus.ModbusInfoApi;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/modbusInfoApi")
@Tag(name = "Modbus基础信息接口")
public class ModbusInfoApiController extends BladeController {

    @Resource
    private ModbusInfoApi modbusInfoApi;

    @GetMapping("/findByProductKey")
    @Operation(summary = "根据产品Key查询Modbus配置信息")
    public ModbusInfo findByProductKey(@Parameter(description = "产品唯一标识") @RequestParam String productKey) {
        return modbusInfoApi.findByProductKey(productKey);
    }
}