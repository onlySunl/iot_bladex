package org.springblade.modules.iot.controller.admin.thingmodel;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelPageReqVO;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelRespVO;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelSaveReqVO;
import org.springblade.modules.iot.service.product.IThingModelService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;



@Tag(name = "管理后台 - 产品物模型")
@RestController
@RequestMapping("/eiot/thing-model")
@Validated
public class ThingModelController extends BladeController {

    @Resource
    private IThingModelService thingModelService;


    @PostMapping("/save")
    @Operation(summary = "更新产品物模型")
    public R<Boolean> saveThingModel(@Valid @RequestBody ThingModelSaveReqVO updateReqVO) {
        thingModelService.saveThingModel(updateReqVO);
        return data(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得产品物模型")
    @Parameter(name = "productKey", description = "productKey", required = true, example = "1024")
    public R<ThingModel> getThingModel(@RequestParam("productKey") String productKey) {
        ThingModel thingModel = thingModelService.getThingModelByProductKey(productKey);
        return data(thingModel);
    }

    @PostMapping("/page")
    @Operation(summary = "获得产品物模型分页")
    public R<PageResult<ThingModelRespVO>> getThingModelPage(@Valid @RequestBody ThingModelPageReqVO pageReqVO) {
        return data(thingModelService.getThingModelPage(pageReqVO));
    }

}
