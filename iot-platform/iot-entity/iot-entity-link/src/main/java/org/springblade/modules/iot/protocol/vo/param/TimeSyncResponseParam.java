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
 * File Name: TimeSyncResponseParam
 * -----------------------------------------------------------------------------
 * Description:
 * TimeSyncResponse
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
 * @date 2024/7/24 15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "TimeSyncResponseParam", description = "Response Data Structure for Time Synchronization")
public class TimeSyncResponseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Server's current system time, in ISO 8601 format", requiredMode = Schema.RequiredMode.REQUIRED)
    private String serverTime;

    @Schema(description = "Server's current system time in milliseconds since epoch", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long serverTimeMillis;

    @Schema(description = "Time zone of the server", requiredMode = Schema.RequiredMode.REQUIRED)
    private String timeZone;
}
