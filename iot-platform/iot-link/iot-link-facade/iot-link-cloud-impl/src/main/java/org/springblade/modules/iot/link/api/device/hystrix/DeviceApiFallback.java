package org.springblade.modules.iot.link.api.device.hystrix;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.link.api.device.DeviceApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: thinglinks-cloud
 * @description: 设备API熔断
 * @packagename: org.springblade.modules.iot.link.api.device.hystrix
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:37
 **/
@Component
public class DeviceApiFallback implements DeviceApi {


    /**
     * 修改设备连接状态
     *
     * @param id               设备ID
     * @param connectionStatus 新连接状态值
     * @return 更新结果
     */
    @Override
    public R<Boolean> updateDeviceConnectionStatus(Long id, Integer connectionStatus) {
        return R.timeout();
    }

    @Override
    public R<List<DeviceDetailsResultVO>> getDeviceDetailsByIdentifications(List<String> deviceIdentifications) {
        return R.timeout();
    }
}
