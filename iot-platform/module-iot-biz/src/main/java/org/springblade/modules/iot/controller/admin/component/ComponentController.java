package org.springblade.modules.iot.controller.admin.component;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.component.vo.*;
import org.springblade.modules.iot.convert.ComponentConvert;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.service.component.IComponentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;



@Tag(name = "管理后台 - 组件配置")
@RestController
@RequestMapping("/eiot/component")
@Validated
public class ComponentController extends BladeController {

    @Resource
    private IComponentService componentService;

    @PostMapping("/create")
    @Operation(summary = "创建组件")
    public R<Long> createComponent(@Valid @RequestBody ComponentCreateReqVO createReqVO) {
        return data(componentService.createComponent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新组件")
    public R<Boolean> updateComponent(@Valid @RequestBody ComponentUpdateReqVO updateReqVO) {
        componentService.updateComponent(updateReqVO);
        return data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除组件")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteComponent(@RequestParam("id") Long id) {
        componentService.deleteComponent(id);
        return data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得组件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<ComponentRespVO> getComponent(@RequestParam("id") Long id) {
        ComponentDO component = componentService.getComponent(id);
        return data(ComponentConvert.INSTANCE.convert(component));
    }

    @GetMapping("/page")
    @Operation(summary = "获得组件配置分页")
    public R<PageResult<ComponentRespVO>> getComponentPage(@Valid ComponentPageReqVO pageVO) {
        PageResult<ComponentDO> pageResult = componentService.getComponentPage(pageVO);
        return data(ComponentConvert.INSTANCE.convertPage(pageResult));
    }

}
