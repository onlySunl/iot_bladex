package org.springblade.modules.iot.api.component;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 组件对外API控制器
 * 与 {@link org.springblade.modules.iot.api.component.service.RemoteIotComponentService} 接口一一对应
 *
 * @author EnjoyIot
 */
@RestController
@RequestMapping("/componentApi")
@Tag(name = "组件对外API", description = "组件基础信息查询接口")
public class ComponentApiController extends BladeController {

    @Resource
    private ComponentApi componentApi;

    @GetMapping("/getInfo")
    @Operation(summary = "根据组件类型获取组件信息")
    public ComponentInfo getInfo(@Parameter(description = "组件类型") @RequestParam String type) {
        return componentApi.getInfo(type);
    }
}