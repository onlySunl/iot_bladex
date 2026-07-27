package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: TopoOtaCommandResponseParam
 * -----------------------------------------------------------------------------
 * Description:
 * OTA 远程升级响应参数
 * 用于设备向物联网平台上报 OTA 升级结果
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/1/18       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/1/18 22:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "TopoOtaCommandResponseParam", description = "Response Data Structure for OTA Command")
public class TopoOtaCommandResponseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "The unique identifier of the device.")
    private String deviceIdentification;

    @Schema(description = "The unique identifier of the OTA task.")
    private Long otaTaskId;

    @Schema(description = "The status of the OTA upgrade: 0 for pending, 1 for in progress, 2 for success, 3 for failure.")
    private Integer upgradeStatus;

    @Schema(description = "The progress of the OTA upgrade in percentage.")
    private Integer progress;

    @Schema(description = "The start time of the OTA upgrade as a timestamp.")
    private Long startTime;

    @Schema(description = "The end time of the OTA upgrade as a timestamp.")
    private Long endTime;

    @Schema(description = "The error code, if any, resulting from the OTA upgrade.")
    private String errorCode;

    @Schema(description = "The error message, if any, resulting from the OTA upgrade.")
    private String errorMessage;

    @Schema(description = "Details about the success of the OTA upgrade.")
    private String successDetails;

    @Schema(description = "Details about the failure of the OTA upgrade.")
    private String failureDetails;

    @Schema(description = "Logs detailing the OTA upgrade process.")
    private String logDetails;
}
