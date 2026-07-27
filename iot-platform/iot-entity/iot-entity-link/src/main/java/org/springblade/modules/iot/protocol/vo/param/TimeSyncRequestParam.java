package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: TimeSyncRequest
 * -----------------------------------------------------------------------------
 * Description:
 * TimeSyncRequest
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/7/24       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/7/24 15:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "TimeSyncRequestParam", description = "Request Data Structure for Time Synchronization")
public class TimeSyncRequestParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for the device", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceIdentification;

    @Schema(description = "Current system time on the device, in ISO 8601 format", requiredMode = Schema.RequiredMode.REQUIRED)
    private String currentDeviceTime;

    @Schema(description = "Current system time in milliseconds since epoch", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long currentDeviceTimeMillis;

    @Schema(description = "Time zone of the device", requiredMode = Schema.RequiredMode.REQUIRED)
    private String timeZone;
}
