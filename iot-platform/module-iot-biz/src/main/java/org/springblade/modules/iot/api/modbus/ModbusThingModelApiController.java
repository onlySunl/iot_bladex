package org.springblade.modules.iot.api.modbus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/modbusThingModelApi")
@Tag(name = "Modbus物模型接口")
public class ModbusThingModelApiController extends BladeController {

    @Resource
    private ModbusThingModelApi modbusThingModelApi;

    @GetMapping("/findByProductKey")
    @Operation(summary = "根据产品Key查询Modbus物模型")
    public ModbusThingModel findByProductKey(@Parameter(description = "产品唯一标识") @RequestParam String productKey) {
        return modbusThingModelApi.findByProductKey(productKey);
    }

    @PostMapping("/save")
    @Operation(summary = "保存/更新Modbus物模型")
    public ModbusThingModel save(@RequestBody ModbusThingModel thingModel) {
        return modbusThingModelApi.save(thingModel);
    }
}