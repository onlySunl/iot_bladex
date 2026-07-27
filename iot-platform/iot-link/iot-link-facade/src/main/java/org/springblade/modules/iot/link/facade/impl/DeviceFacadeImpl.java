package org.springblade.modules.iot.link.facade.impl;

import org.springblade.common.base.R;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.link.api.device.DeviceApi;
import org.springblade.modules.iot.link.facade.DeviceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Service
public class DeviceFacadeImpl implements DeviceFacade {
    @Lazy
    @Autowired
    private DeviceApi deviceApi;

    @Override
    public R<Boolean> updateDeviceConnectionStatus(Long id, Integer connectionStatus) {
        return deviceApi.updateDeviceConnectionStatus(id, connectionStatus);
    }

    @Override
    public R<List<DeviceDetailsResultVO>> getDeviceDetailsByIdentifications(List<String> deviceIdentifications) {
        return deviceApi.getDeviceDetailsByIdentifications(deviceIdentifications);
    }
}
