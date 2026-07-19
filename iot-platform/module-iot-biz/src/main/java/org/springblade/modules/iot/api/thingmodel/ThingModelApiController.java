package org.springblade.modules.iot.api.thingmodel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 物模型对外API控制器，与RemoteIotThingModelService一一对应
 */
@RestController
@RequestMapping("/thingModelApi")
@Tag(name = "物模型API", description = "缓存物模型查询接口")
public class ThingModelApiController extends BladeController {

    @Resource
    private ThingModelApi thingModelApi;

    @GetMapping("/getThingModelByProductKeyFromCache")
    @Operation(summary = "根据产品Key从缓存获取物模型")
    public ThingModel getThingModelByProductKeyFromCache(@Parameter(description = "产品唯一标识productKey") @RequestParam String pk) {
        return thingModelApi.getThingModelByProductKeyFromCache(pk);
    }
}