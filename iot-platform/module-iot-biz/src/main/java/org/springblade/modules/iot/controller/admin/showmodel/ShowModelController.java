package org.springblade.modules.iot.controller.admin.showmodel;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelRespVO;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelSaveReqVO;
import org.springblade.modules.iot.service.product.IShowModelService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;




@Tag(name = "管理后台 - 产品显示模型")
@RestController
@RequestMapping("/eiot/show-model")
@Validated
public class ShowModelController extends BladeController {

    @Resource
    private IShowModelService showModelService;


    @PostMapping("/save")
    @Operation(summary = "保存产品显示模型")
    public R<Boolean> updateShowModel(@Valid @RequestBody ShowModelSaveReqVO updateReqVO) {
        showModelService.saveShowModel(updateReqVO);
        return data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品显示模型")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteShowModel(@RequestParam("id") Long id) {
        showModelService.deleteShowModel(id);
        return data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品显示模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<ShowModelRespVO> getShowModel(@RequestParam("id") Long id) {
        ShowModelRespVO showModel = showModelService.getShowModel(id);
        return data(showModel);
    }

    @GetMapping("/getByProductKey")
    @Operation(summary = "获得产品显示模型")
    @Parameter(name = "productKey", description = "productKey", required = true, example = "1024")
    public R<List<ShowModelRespVO>> getShowModelByProductKey(@RequestParam("productKey") String id) {
        List<ShowModelRespVO> res = showModelService.getShowModelByProductKey(id);
        return data(res);
    }


}
