package org.springblade.modules.iot.link.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.link.api.device.DeviceCommandApi;
import org.springblade.modules.iot.link.facade.DeviceCommandFacade;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author tangyh
 * @since 2024/12/24 17:01
 */
@Service
public class DeviceCommandFacadeImpl implements DeviceCommandFacade {
    @Lazy
    @Autowired
    private DeviceCommandApi deviceCommandApi;

    @Override
    public R<DeviceCommand> saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        return deviceCommandApi.saveDeviceCommand(deviceCommandSaveVO);
    }

    @Override
    public R<?> issueCommands(DeviceCommandWrapperParam commandWrapper) {
        return deviceCommandApi.issueCommands(commandWrapper);
    }
}
