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
 * File Name: TopoOtaPullResponseParam
 * -----------------------------------------------------------------------------
 * Description:
 * OTA 拉取平台的最新软固件信息响应参数
 * 参数同 TopoOtaCommandRequestParam 一致
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/3/20       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/3/20 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "TopoOtaPullResponseParam", description = "Response Data Structure for OTA Pulling Platform's Latest Software and Firmware Information")
public class TopoOtaPullResponseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for the device")
    private String deviceIdentification;

    @Schema(description = "Unique identifier for the product")
    private String productIdentification;

    @Schema(description = "Unique identifier for the OTA task")
    private Long otaTaskId;

    @Schema(description = "OTA upgrade task name")
    private String otaTaskName;

    @Schema(description = "Name of the OTA package")
    private String packageName;

    @Schema(description = "Type of OTA package")
    private Integer packageType;

    @Schema(description = "Version of the OTA package")
    private String version;

    @Schema(description = "Location of the OTA package file")
    private String fileLocation;

    @Schema(description = "Signature method of the OTA package")
    private Integer signMethod;

    @Schema(description = "Signature of the OTA package")
    private String sign;

    @Schema(description = "Description of the OTA package")
    private String description;

    @Schema(description = "Custom information for the OTA package")
    private String customInfo;

}
