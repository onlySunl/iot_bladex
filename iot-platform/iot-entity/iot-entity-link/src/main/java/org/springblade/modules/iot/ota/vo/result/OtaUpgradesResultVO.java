package org.springblade.modules.iot.ota.vo.result;

import java.io.Serial;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单查询方法返回值VO
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "OtaUpgradesResultVO", description = "OTA升级包")
public class OtaUpgradesResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 包名称
     */
    @Schema(description = "包名称")
    private String packageName;
    /**
     * 升级包类型(0:软件包、1:固件包)
     */
    @Schema(description = "升级包类型(0:软件包、1:固件包)")
    private Integer packageType;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;
    /**
     * 升级包版本号
     */
    @Schema(description = "升级包版本号")
    private String version;
    /**
     * 目标产品版本号(影子版本)
     */
    @Schema(description = "目标产品版本号(影子版本)")
    private String productVersionNo;
    /**
     * 升级包的位置
     */
    @Schema(description = "升级包的位置")
    private String fileLocation;

    /**
     * 升级包的签名方法
     */
    @Schema(description = "升级包的签名方法")
    private Integer signMethod;
    /**
     * 状态
     */
    /**
     * 升级包功能描述
     */
    @Schema(description = "升级包功能描述")
    private String description;
    /**
     * 自定义信息
     */
    @Schema(description = "自定义信息")
    private String customInfo;
    /**
     * 描述
     */

}
