package org.springblade.modules.iot.controller.admin.virtualdevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.*;
import org.springblade.modules.iot.service.virtualdevice.VirtualDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springblade.modules.iot.common.entity.CommonResult.success;

@Slf4j
@Tag(name = "管理后台 - 虚拟设备")
@Validated
@RestController
@RequestMapping("/eiot/virtual_device")
public class EiotVirtualDeviceController {

    @Autowired
    private VirtualDeviceService virtualDeviceService;


    @PostMapping("/list")
    @Operation(summary = "获得规则引擎分页")
    public CommonResult<PageResult<VirtualDevice>> selectPage(@Valid @RequestBody VirtualDevicePageReqVO reqVO) {
        PageResult<VirtualDevice> pageResult = virtualDeviceService.selectPage(reqVO);
        return success(BeanUtils.toBean(pageResult, VirtualDevice.class));
    }

    @GetMapping("/getDetail")
    @Operation(summary = "获取虚拟设备")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public CommonResult<VirtualDevice> getVirtualDevice(@RequestParam("id") Long id) {
        VirtualDevice virtualDevice = virtualDeviceService.getVirtualDevice(id);
        return success(virtualDevice);
    }

    /**
     * 添加虚拟设备
     */
    @PostMapping("/add")
    @Operation(summary = "添加虚拟设备")
    public CommonResult<Long> addVirtualDevice(@Valid @RequestBody EiotVirtualDeviceSaveReqVO virtualDevice) {
        return success(virtualDeviceService.saveVirtualDevice(virtualDevice));
    }

    /**
     * 修改虚拟设备
     */
    @Operation(summary = "更新虚拟设备")
    @PostMapping("/update")
    public CommonResult<Boolean> updateVirtualDevice(@RequestBody VirtualDevice virtualDevice) {
        virtualDeviceService.updateVirtualDevice(virtualDevice);
        return CommonResult.success(true);
    }

    /**
     * 保存虚拟设备映射
     */
    @Operation(summary = "保存虚拟设备映射")
    @PostMapping("/saveDevices")
    public CommonResult<Boolean> saveDevices(@RequestBody EiotVirtualSaveDevicesMappingVo virtualDevice) {
        virtualDeviceService.saveVirtualDeviceMapping(virtualDevice);
        return CommonResult.success(true);
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchDelete")
    @Operation(summary = "删除虚拟设备")
    @Parameter(name = "id", description = "设备id", required = true)
    public CommonResult<Boolean> batchDeleteVirtualDevice(@RequestBody List<Long> ids) {
        ids.forEach(this::deleteVirtualDevice);
        return success(true);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @Operation(summary = "删除虚拟设备")
    @Parameter(name = "id", description = "设备id", required = true)
    public CommonResult<Boolean> deleteVirtualDevice(@RequestBody Long id) {
        virtualDeviceService.deleteVirtualDevice(id);
        return success(true);
    }

    @PostMapping("/run")
    @Operation(summary = "手动执行虚拟设备")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public CommonResult<Boolean> run(@RequestBody IdReqVo reqVo) {
        virtualDeviceService.run(reqVo.getId());
        return success(true);
    }


    @PostMapping("/setState")
    @Operation(summary = "设置虚拟设备状态")
    @Parameter(name = "id", description = "虚拟设备设备id", required = true, example = "1024")
    public CommonResult<Boolean> setState(@Valid @RequestBody EiotVirtualDeviceSetStateReqVO reqest) {
        if (!VirtualDevice.STATE_RUNNING.equals(reqest.getState())
                && !VirtualDevice.STATE_STOPPED.equals(reqest.getState())) {
            throw new RuntimeException("state is illegal");
        }
        virtualDeviceService.setState(reqest.getId(), reqest.getState());
        return success(true);
    }

    @PostMapping("/saveScript")
    @Operation(summary = "保存运行脚本")
    @Parameter(name = "id", description = "保存运行脚本", required = true, example = "1024")
    public CommonResult<Boolean> getVirtualDevice(@Valid @RequestBody EiotVirtualSaveScriptVo saveScriptVo) {
        virtualDeviceService.saveScript(saveScriptVo);
        return success(true);
    }


    /**
     * 取虚拟设备执行日志
     */
    @PostMapping("/logs/list")
    @Operation(summary = "取虚拟设备执行日志")
    @Parameter(name = "id", description = "取虚拟设备执行日志", required = true, example = "1024")
    public CommonResult<PageResult<VirtualDeviceLog>> getLogs(@Validated @RequestBody VirtualDeviceLogPageReqVO data) {
        PageResult<VirtualDeviceLog> pageResult = virtualDeviceService.findByVirtualDeviceId(data.getVirtualDeviceId(), data.getPageNo(), data.getPageSize());
        return CommonResult.success(pageResult);
    }
}
