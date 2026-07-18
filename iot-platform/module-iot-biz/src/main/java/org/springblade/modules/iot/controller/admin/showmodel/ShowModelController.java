

package org.springblade.modules.iot.controller.admin.showmodel;

import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelRespVO;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelSaveReqVO;
import org.springblade.modules.iot.service.product.ShowModelService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import java.util.*;

import static org.springblade.modules.iot.common.entity.CommonResult.success;


@Tag(name = "管理后台 - 产品显示模型")
@RestController
@RequestMapping("/eiot/show-model")
@Validated
public class ShowModelController {

    @Resource
    private ShowModelService showModelService;


    @PostMapping("/save")
    @Operation(summary = "保存产品显示模型")
    @PreAuthorize("@ss.hasPermission('iot:show-model:update')")
    public CommonResult<Boolean> updateShowModel(@Valid @RequestBody ShowModelSaveReqVO updateReqVO) {
        showModelService.saveShowModel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品显示模型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:show-model:delete')")
    public CommonResult<Boolean> deleteShowModel(@RequestParam("id") Long id) {
        showModelService.deleteShowModel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品显示模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:show-model:query')")
    public CommonResult<ShowModelRespVO> getShowModel(@RequestParam("id") Long id) {
        ShowModelRespVO showModel = showModelService.getShowModel(id);
        return success(showModel);
    }

    @GetMapping("/getByProductKey")
    @Operation(summary = "获得产品显示模型")
    @Parameter(name = "productKey", description = "productKey", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:show-model:query')")
    public CommonResult<List<ShowModelRespVO>> getShowModelByProductKey(@RequestParam("productKey") String id) {
        List<ShowModelRespVO> res = showModelService.getShowModelByProductKey(id);
        return success(res);
    }


}
