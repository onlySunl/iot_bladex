package org.springblade.modules.iot.api.virtualdevice;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 虚拟设备对外API控制器
 * 与 {@link org.springblade.modules.iot.api.virtualdevice.service.RemoteIotVirtualDeviceService} 一一对应
 */
@RestController
@RequestMapping("/virtualDeviceApi")
@Tag(name = "虚拟设备API", description = "根据触发器、状态查询虚拟设备列表")
public class VirtualDeviceApiController extends BladeController {

    @Resource
    private VirtualDeviceApi virtualDeviceApi;

    @GetMapping("/findByTriggerAndState")
    @Operation(summary = "根据触发器表达式+状态查询虚拟设备")
    public List<VirtualDevice> findByTriggerAndState(
            @Parameter(description = "触发器表达式") @RequestParam String trigger,
            @Parameter(description = "设备状态") @RequestParam String state
    ) {
        return virtualDeviceApi.findByTriggerAndState(trigger, state);
    }
}