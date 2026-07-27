package org.springblade.modules.iot.ota.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: SendDeviceOtaUpgradeCommandRequestParam
 * -----------------------------------------------------------------------------
 * Description:
 * Send an OTA upgrade command to a specified device params
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2025/2/9       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2025/2/9 16:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(title = "SendDeviceOtaUpgradeCommandRequestParam", description = "Send an OTA upgrade command to a specified device params.")
public class SendDeviceOtaUpgradeCommandRequestParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique task ID for the OTA upgrade task.")
    @NotNull(message = "Upgrade task ID cannot be empty")
    private Long upgradeTaskId;

    @Schema(description = "List of device identifiers for devices intended to receive the OTA (Over-the-Air) upgrade. A maximum of 100 devices can be included in this list.")
    @Size(max = 100, message = "The list can contain a maximum of 10 device identifiers")
    private List<String> deviceIdentificationList;

}
