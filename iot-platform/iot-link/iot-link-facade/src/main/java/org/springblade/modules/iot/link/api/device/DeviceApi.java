package org.springblade.modules.iot.link.api.device;

import org.springblade.common.base.R;
import org.springblade.common.constant.Constants;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.link.api.device.hystrix.DeviceApiFallback;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: thinglinks-cloud
 * @description: 设备管理API
 * @packagename: com.mqttsnet.thinglinks.link.api.device
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 18:20
 **/
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:thinglinks-link-server}",
        fallback = DeviceApiFallback.class, path = "/device")
public interface DeviceApi {

    /**
     * 修改设备连接状态
     *
     * @param id               设备ID
     * @param connectionStatus 新连接状态值
     * @return 更新结果
     */
    @Operation(summary = "修改设备连接状态", description = "根据设备ID修改设备连接状态")
    @PutMapping("/updateDeviceConnectionStatus/{id}")
    R<Boolean> updateDeviceConnectionStatus(@Parameter(description = "设备ID", required = true) @PathVariable("id") Long id,
                                            @Parameter(description = "新连接状态值（0:未连接、1:在线、2:离线）", required = true, example = "0,1,2") @RequestParam("connectionStatus") Integer connectionStatus);


    /**
     * 根据多个设备标识获取设备详情
     *
     * @param deviceIdentifications 设备标识列表
     * @return 设备详情列表
     */
    @Operation(summary = "根据多个设备标识获取设备详情", description = "根据多个设备标识获取设备详情(多个英文逗号分割)")
    @GetMapping("/getDeviceDetailsByIdentifications")
    @Parameters({@Parameter(name = "deviceIdentifications", description = "设备标识列表", required = true),})
    R<List<DeviceDetailsResultVO>> getDeviceDetailsByIdentifications(@RequestParam List<String> deviceIdentifications);


}
