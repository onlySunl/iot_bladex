package org.springblade.modules.iot.link.api.device;

import org.springblade.basic.base.R;
import org.springblade.basic.constant.Constants;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.link.api.device.hystrix.DeviceCommandApiFallback;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceCommandApi.java
 * -----------------------------------------------------------------------------
 * Description:
 * DeviceCommand  API
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
 * @date 2023-11-12 03:20
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:thinglinks-link-server}",
        fallback = DeviceCommandApiFallback.class, path = "/deviceCommand")
public interface DeviceCommandApi {


    /**
     * Creates a new device command entry in the database.
     *
     * @param deviceCommandSaveVO The device command data to be saved.
     * @return The saved device command data.
     */
    @Operation(summary = "Create Device Command", description = "Saves a new device command to the database.")
    @PostMapping("/save")
    R<DeviceCommand> saveDeviceCommand(@RequestBody DeviceCommandSaveVO deviceCommandSaveVO);


    /**
     * Issues a list of commands to devices, handling both serial and parallel execution.
     *
     * @param commandWrapper The list of commands to be issued.
     * @return The result of the command execution.
     */
    @Operation(summary = "Issue commands to devices", description = "Issues a list of commands to devices, handling both serial and parallel execution.")
    @PostMapping("/issueCommands")
    R<?> issueCommands(@RequestBody @Valid DeviceCommandWrapperParam commandWrapper);

}
