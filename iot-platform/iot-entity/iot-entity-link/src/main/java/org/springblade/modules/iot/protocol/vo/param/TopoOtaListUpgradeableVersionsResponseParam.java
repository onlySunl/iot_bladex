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
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: TopoOtaListUpgradeableVersionsResponseParam
 * -----------------------------------------------------------------------------
 * Description:
 * OTA 获取可升级版本列表响应参数
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2026/2/9      mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "TopoOtaListUpgradeableVersionsResponseParam", description = "Response Data Structure for OTA Listing Upgradeable Versions")
public class TopoOtaListUpgradeableVersionsResponseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for the device", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceIdentification;

    @Schema(description = "Unique identifier for the product", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productIdentification;

    @Schema(description = "Type of OTA package", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer packageType;

    @Schema(description = "Current version of the OTA package")
    private String currentVersion;

    @Schema(description = "List of upgradeable version information")
    private List<UpgradeVersionInfo> upgradeVersions;

    /**
     * 可升级版本信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Schema(title = "UpgradeVersionInfo", description = "Upgradeable Version Information")
    public static class UpgradeVersionInfo implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "OTA task ID")
        private Long otaTaskId;

        @Schema(description = "OTA upgrade task name")
        private String otaTaskName;

        @Schema(description = "Name of the OTA package")
        private String packageName;

        @Schema(description = "Version of the OTA package")
        private String version;

        @Schema(description = "Location of the OTA package file")
        private String fileLocation;

        @Schema(description = "Description of the OTA package")
        private String description;

        @Schema(description = "Custom information for the OTA package")
        private String customInfo;

        @Schema(description = "Signature method of the OTA package")
        private Integer signMethod;

        @Schema(description = "Signature of the OTA package")
        private String sign;
    }

}
