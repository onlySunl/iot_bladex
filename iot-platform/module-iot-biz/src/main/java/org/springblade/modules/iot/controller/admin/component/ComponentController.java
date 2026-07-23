package org.springblade.modules.iot.controller.admin.component;

import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.component.vo.*;
import org.springblade.modules.iot.convert.ComponentConvert;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.service.component.ComponentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static org.springblade.modules.iot.common.entity.CommonResult.success;

@Tag(name = "管理后台 - 组件配置")
@RestController
@RequestMapping("/eiot/component")
@Validated
public class ComponentController {

    @Resource
    private ComponentService componentService;

    @PostMapping("/create")
    @Operation(summary = "创建组件")
    public CommonResult<Long> createComponent(@Valid @RequestBody ComponentCreateReqVO createReqVO) {
        return success(componentService.createComponent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新组件")
    public CommonResult<Boolean> updateComponent(@Valid @RequestBody ComponentUpdateReqVO updateReqVO) {
        componentService.updateComponent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除组件")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteComponent(@RequestParam("id") Long id) {
        componentService.deleteComponent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得组件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ComponentRespVO> getComponent(@RequestParam("id") Long id) {
        ComponentDO component = componentService.getComponent(id);
        return success(ComponentConvert.INSTANCE.convert(component));
    }

    @GetMapping("/page")
    @Operation(summary = "获得组件配置分页")
    public CommonResult<PageResult<ComponentRespVO>> getComponentPage(@Valid ComponentPageReqVO pageVO) {
        PageResult<ComponentDO> pageResult = componentService.getComponentPage(pageVO);
        return success(ComponentConvert.INSTANCE.convertPage(pageResult));
    }

}
