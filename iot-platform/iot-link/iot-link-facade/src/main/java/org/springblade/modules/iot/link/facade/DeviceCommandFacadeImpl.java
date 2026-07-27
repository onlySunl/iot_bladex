package org.springblade.modules.iot.link.facade;

import org.springblade.common.base.R;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.service.DeviceCommandService;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tangyh
 * @since 2024/12/24 17:01
 */
@Service
public class DeviceCommandFacadeImpl implements DeviceCommandFacade {
    @Autowired
    private DeviceCommandService deviceCommandService;

    @Override
    public R<DeviceCommand> saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        DeviceCommand savedDeviceCommand = deviceCommandService.saveDeviceCommand(deviceCommandSaveVO);
        return R.success(savedDeviceCommand);
    }

    @Override
    public R<?> issueCommands(DeviceCommandWrapperParam commandWrapper) {
        deviceCommandService.processDeviceCommands(commandWrapper);
        return R.success();
    }
}
