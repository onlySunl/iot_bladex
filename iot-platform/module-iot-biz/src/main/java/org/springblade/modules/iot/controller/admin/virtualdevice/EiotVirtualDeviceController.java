package org.springblade.modules.iot.controller.admin.virtualdevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.*;
import org.springblade.modules.iot.service.virtualdevice.IVirtualDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springblade.core.boot.ctrl.BladeController;



@Slf4j
@Tag(name = "管理后台 - 虚拟设备")
@Validated
@RestController
@RequestMapping("/eiot/virtual_device")
public class EiotVirtualDeviceController extends BladeController {

    @Autowired
    private IVirtualDeviceService virtualDeviceService;


    @PostMapping("/list")
    @Operation(summary = "获得规则引擎分页")
    public R<PageResult<VirtualDevice>> selectPage(@Valid @RequestBody VirtualDevicePageReqVO reqVO) {
        PageResult<VirtualDevice> pageResult = virtualDeviceService.selectPage(reqVO);
        return data(BeanUtils.toBean(pageResult, VirtualDevice.class));
    }

    @GetMapping("/getDetail")
    @Operation(summary = "获取虚拟设备")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public R<VirtualDevice> getVirtualDevice(@RequestParam("id") Long id) {
        VirtualDevice virtualDevice = virtualDeviceService.getVirtualDevice(id);
        return data(virtualDevice);
    }

    /**
     * 添加虚拟设备
     */
    @PostMapping("/add")
    @Operation(summary = "添加虚拟设备")
    public R<Long> addVirtualDevice(@Valid @RequestBody EiotVirtualDeviceSaveReqVO virtualDevice) {
        return data(virtualDeviceService.saveVirtualDevice(virtualDevice));
    }

    /**
     * 修改虚拟设备
     */
    @Operation(summary = "更新虚拟设备")
    @PostMapping("/update")
    public R<Boolean> updateVirtualDevice(@RequestBody VirtualDevice virtualDevice) {
        virtualDeviceService.updateVirtualDevice(virtualDevice);
        return data(true);
    }

    /**
     * 保存虚拟设备映射
     */
    @Operation(summary = "保存虚拟设备映射")
    @PostMapping("/saveDevices")
    public R<Boolean> saveDevices(@RequestBody EiotVirtualSaveDevicesMappingVo virtualDevice) {
        virtualDeviceService.saveVirtualDeviceMapping(virtualDevice);
        return data(true);
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchDelete")
    @Operation(summary = "删除虚拟设备")
    @Parameter(name = "id", description = "设备id", required = true)
    public R<Boolean> batchDeleteVirtualDevice(@RequestBody List<Long> ids) {
        ids.forEach(this::deleteVirtualDevice);
        return data(true);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @Operation(summary = "删除虚拟设备")
    @Parameter(name = "id", description = "设备id", required = true)
    public R<Boolean> deleteVirtualDevice(@RequestBody Long id) {
        virtualDeviceService.deleteVirtualDevice(id);
        return data(true);
    }

    @PostMapping("/run")
    @Operation(summary = "手动执行虚拟设备")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public R<Boolean> run(@RequestBody IdReqVo reqVo) {
        virtualDeviceService.run(reqVo.getId());
        return data(true);
    }


    @PostMapping("/setState")
    @Operation(summary = "设置虚拟设备状态")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public R<Boolean> setState(@Valid @RequestBody EiotVirtualDeviceSetStateReqVO reqest) {
        if (!VirtualDevice.STATE_RUNNING.equals(reqest.getState())
                && !VirtualDevice.STATE_STOPPED.equals(reqest.getState())) {
            throw new RuntimeException("state is illegal");
        }
        virtualDeviceService.setState(reqest.getId(), reqest.getState());
        return data(true);
    }

    @PostMapping("/saveScript")
    @Operation(summary = "保存运行脚本")
    @Parameter(name = "id", description = "保存运行脚本", required = true, example = "1024")
    public R<Boolean> getVirtualDevice(@Valid @RequestBody EiotVirtualSaveScriptVo saveScriptVo) {
        virtualDeviceService.saveScript(saveScriptVo);
        return data(true);
    }


    /**
     * 取虚拟设备执行日志
     */
    @PostMapping("/logs/list")
    @Operation(summary = "取虚拟设备执行日志")
    @Parameter(name = "id", description = "取虚拟设备执行日志", required = true, example = "1024")
    public R<PageResult<VirtualDeviceLog>> getLogs(@Validated @RequestBody VirtualDeviceLogPageReqVO data) {
        PageResult<VirtualDeviceLog> pageResult = virtualDeviceService.findByVirtualDeviceId(data.getVirtualDeviceId(), data.getPageNo(), data.getPageSize());
        return data(pageResult);
    }
}
