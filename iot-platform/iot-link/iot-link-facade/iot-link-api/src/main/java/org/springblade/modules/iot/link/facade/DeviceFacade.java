package org.springblade.modules.iot.link.facade;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;

import java.util.List;

/**
 * @program: thinglinks-cloud
 * @description: 设备管理API
 * @packagename: org.springblade.modules.iot.link.api.device
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 18:20
 **/
public interface DeviceFacade {

    /**
     * 修改设备连接状态
     *
     * @param id               设备ID
     * @param connectionStatus 新连接状态值
     * @return 更新结果
     */
    R<Boolean> updateDeviceConnectionStatus(Long id,
                                            Integer connectionStatus);


    /**
     * 根据多个设备标识获取设备详情
     *
     * @param deviceIdentifications 设备标识列表
     * @return 设备详情列表
     */
    R<List<DeviceDetailsResultVO>> getDeviceDetailsByIdentifications(List<String> deviceIdentifications);


}
