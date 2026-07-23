package org.springblade.modules.iot.controller.admin.ota;


import cn.hutool.core.util.ObjectUtil;

import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageResult;

import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.controller.admin.ota.vo.*;
import org.springblade.modules.iot.service.ota.OtaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static org.springblade.modules.iot.api.enums.ErrorCodeConstants.FILE_NOT_NULL;
import static org.springblade.modules.iot.common.utils.ServiceExceptionUtil.exception;

/**
 * @Author: Enjoy-iot
 * @Date: 2023/5/19 20:42
 * @Description:
 */
@Tag(name= "ota升级管理")
@Slf4j
@RestController
@RequestMapping("/eiot/ota")
public class OtaController  {

    @Resource
    private OtaService otaService;

    @Operation(summary ="升级包上传")
    @PostMapping(value = "/package/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<OtaPackageUploadVo> packageUpload(@RequestPart("file") MultipartFile file) throws Exception {
        if (ObjectUtil.isNull(file)) {
            throw exception(FILE_NOT_NULL);
        }
        return CommonResult.success(otaService.uploadFile(file));
    }

    @Operation(summary ="新增升级包")
    @PostMapping("/package/add")
    public CommonResult<Long> packageAdd(@RequestBody @Valid OtaPackageBo request) {
        return CommonResult.success(otaService.addOtaPackage(request));
    }

    @Operation(summary ="删除升级包")
    @PostMapping("/package/delById")
    public CommonResult<Boolean> delPackageById(@RequestBody @Valid IdReqVo request) {
        return CommonResult.success(otaService.delOtaPackageById(request.getId()));
    }

    @Operation(summary ="升级包列表")
    @PostMapping("/package/getList")
    public CommonResult<PageResult<OtaPackage>> packageList(@RequestBody @Validated OtaPackagePageReq request) {
        return CommonResult.success(otaService.getOtaPackagePageList(request));
    }

    @Operation(summary ="OTA升级")
    @PostMapping("/device/upgrade")
    public CommonResult<DeviceUpgradeVo> deviceUpgrade(@RequestBody DeviceUpgradeBo request) {
        String result = otaService.startUpgrade(request.getOtaId(), request.getDeviceIds());
        return CommonResult.success(DeviceUpgradeVo.builder().result(result).build());
    }

    @Operation(summary ="设备升级结果查询")
    @PostMapping("/device/detail")
    public CommonResult<PageResult<DeviceOtaDetailVo>> otaDeviceDetail(@RequestBody DeviceOtaDetailPageReq request) {
        return CommonResult.success(otaService.otaDeviceDetail(request));
    }

    @Operation(summary ="设备升级批次查询")
    @PostMapping("/device/info")
    public CommonResult<PageResult<DeviceOtaInfoVo>> otaDeviceInfo(@RequestBody DeviceOtaPageReq request) {
        return CommonResult.success(otaService.otaDeviceInfo(request));
    }

    @Operation(summary ="ota升级测试')")
    @PostMapping("/testStartUpgrade")
    public void testStartUpgrade( ) {
        otaService.testStartUpgrade();
    }

}
