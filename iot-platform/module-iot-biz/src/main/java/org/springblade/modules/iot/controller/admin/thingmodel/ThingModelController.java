package org.springblade.modules.iot.controller.admin.thingmodel;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelSaveReqVO;
import org.springblade.modules.iot.service.product.ThingModelService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import static org.springblade.modules.iot.common.entity.CommonResult.success;


@Tag(name = "管理后台 - 产品物模型")
@RestController
@RequestMapping("/eiot/thing-model")
@Validated
public class ThingModelController {

    @Resource
    private ThingModelService thingModelService;


    @PostMapping("/save")
    @Operation(summary = "更新产品物模型")
    public CommonResult<Boolean> saveThingModel(@Valid @RequestBody ThingModelSaveReqVO updateReqVO) {
        thingModelService.saveThingModel(updateReqVO);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得产品物模型")
    @Parameter(name = "productKey", description = "productKey", required = true, example = "1024")
    public CommonResult<ThingModel> getThingModel(@RequestParam("productKey") String productKey) {
        ThingModel thingModel = thingModelService.getThingModelByProductKey(productKey);
        return success(thingModel);
    }

}
