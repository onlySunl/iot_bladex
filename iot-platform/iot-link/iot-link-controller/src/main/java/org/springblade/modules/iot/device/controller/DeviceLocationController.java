package org.springblade.modules.iot.device.controller;

import org.springblade.core.tool.api.R;
import org.springblade.core.mvc.controller.BaseController;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.service.DeviceLocationService;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceLocationSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceLocationUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceLocation")
@Tag(name = "设备位置")
public class DeviceLocationController extends BaseController<DeviceLocationService, Long, DeviceLocation, DeviceLocationSaveVO,
        DeviceLocationUpdateVO, DeviceLocationPageQuery, DeviceLocationResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 新增设备位置信息
     *
     * @param deviceLocationSaveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存设备位置信息", description = "保存设备位置信息")
    @PostMapping("/saveDeviceLocation")
    public R<DeviceLocationSaveVO> saveDeviceLocation(@RequestBody DeviceLocationSaveVO deviceLocationSaveVO) {
        return R.success(superService.saveDeviceLocation(deviceLocationSaveVO));
    }


    /**
     * 修改设备位置信息
     *
     * @param deviceLocationUpdateVO 更新参数
     * @return 更新后的实体
     */
    @Operation(summary = "更新设备位置信息", description = "更新设备位置信息")
    @PutMapping("/updateDeviceLocation")
    public R<DeviceLocationUpdateVO> updateDeviceLocation(@RequestBody DeviceLocationUpdateVO deviceLocationUpdateVO) {
        return R.success(superService.updateDeviceLocation(deviceLocationUpdateVO));
    }

}


