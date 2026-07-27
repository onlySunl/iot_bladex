package org.springblade.modules.iot.ota.vo.param;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description:
 * Device OTA upgrade APP confirmation request parameters.
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/12/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(title = "DeviceOtaUpgradeAppConfirmationParam", description = "Device OTA upgrade APP confirmation request parameters.")
public class DeviceOtaUpgradeAppConfirmationParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Tenant ID")
    @NotNull(message = "Tenant ID cannot be empty")
    private String tenantId;

    @Schema(description = "Unique task ID for the OTA upgrade task.")
    @NotNull(message = "Upgrade task ID cannot be empty")
    private Long taskId;

    @Schema(description = "List of device identifiers for the devices receiving the OTA upgrade.")
    @NotNull(message = "Device identification list cannot be empty")
    private List<String> deviceIdentificationList;

    @Schema(description = "APP confirmation status (true: confirmed, false: rejected)")
    @NotNull(message = "APP confirmation status cannot be empty")
    private Boolean appConfirmationStatus;

}