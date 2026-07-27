package org.springblade.modules.iot.link.api.device.hystrix;

import org.springblade.common.base.R;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.link.api.device.DeviceCommandApi;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springframework.stereotype.Component;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceCommandApiFallback.java
 * -----------------------------------------------------------------------------
 * Description:
 * DeviceCommandApi fusing
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-12 03:21
 */
@Component
public class DeviceCommandApiFallback implements DeviceCommandApi {

    /**
     * Creates a new device command entry in the database.
     *
     * @param deviceCommandSaveVO The device command data to be saved.
     * @return The saved device command data.
     */
    @Override
    public R<DeviceCommand> saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        return R.timeout();
    }

    /**
     * Issues a list of commands to devices, handling both serial and parallel execution.
     *
     * @param commandWrapper The list of commands to be issued.
     * @return The result of the command execution.
     */
    @Override
    public R<?> issueCommands(DeviceCommandWrapperParam commandWrapper) {
        return R.timeout();
    }
}
