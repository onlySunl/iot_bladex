package org.springblade.modules.iot.controller.admin.iot;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.controller.admin.iot.vo.GetDeviceServicePropertyBo;
import org.springblade.modules.iot.controller.admin.iot.vo.DeviceIdReqVo;
import org.springblade.modules.iot.controller.admin.iot.vo.ServiceInvokeReqVo;
import org.springblade.modules.iot.controller.admin.iot.vo.SetDeviceServicePropertyBo;
import org.springblade.modules.iot.service.device.IDeviceCtrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springblade.core.boot.ctrl.BladeController;



@Tag(name = "管理后台-设备控制接口")
@Slf4j
@RestController
@RequestMapping({"/eiot/device", "/eiot/device-ctrl"})
public class DeviceCtrlController extends BladeController {


    @Autowired
    private IDeviceCtrlService deviceCtrlService;


    @Operation(summary = "服务调用")
    @PostMapping("/service/invoke")
    public R<String> invokeService(@RequestBody @Validated ServiceInvokeReqVo request) {
        deviceCtrlService.invokeService(request.getDeviceId(), request.getService(), request.getArgs());
        return data("");
    }

    @Operation(summary = "属性获取")
    @PostMapping("/service/property/get")
    public R<String> invokeServicePropertySet(@RequestBody @Validated GetDeviceServicePropertyBo request) {
        deviceCtrlService.getProperty(request.getDeviceId(), request.getPropertyNames(), true);
        return data("");
    }

    @Operation(summary = "属性设置")
    @PostMapping("/service/property/set")
    public R<String> setProperty(@RequestBody @Validated SetDeviceServicePropertyBo request) {
        deviceCtrlService.setProperty(request.getDeviceId(), request.getArgs());
        return data("");
    }

    /**
     * 设备配置下发
     */
    @Operation(summary = "设备配置下发")
    @PostMapping("/config/send")
    public R<String> sendConfig(@Validated @RequestBody DeviceIdReqVo reqVo) {
        deviceCtrlService.sendConfig(reqVo.getDeviceId());
        return data("");
    }


//    @Operation(summary = "模拟设备上报")
//
//    @PostMapping("/simulateSend")
//    public boolean simulateSend(
//            @Validated @RequestBody ThingModelMessageBo bo) {
//        ThingModelMessage message = bo.to(ThingModelMessage.class);
//        return deviceServiceImpl.simulateSend(message);
//    }

}
