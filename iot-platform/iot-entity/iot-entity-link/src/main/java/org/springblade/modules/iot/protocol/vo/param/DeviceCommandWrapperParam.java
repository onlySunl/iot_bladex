package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * File Name: DeviceCommandWrapperParam.java
 * -----------------------------------------------------------------------------
 * Description:
 * Device Command Request Wrapper Data Structure
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
 * @date 2023-11-11 14:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "DeviceCommandWrapperParam", description = "Device Command Request Wrapper Data Structure")
public class DeviceCommandWrapperParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "List of serial command requests")
    private List<CommandIssueRequestParam> serial;

    @Schema(description = "List of parallel command requests")
    private List<CommandIssueRequestParam> parallel;
}
