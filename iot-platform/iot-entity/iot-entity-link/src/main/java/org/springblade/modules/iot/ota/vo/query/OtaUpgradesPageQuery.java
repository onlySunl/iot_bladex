package org.springblade.modules.iot.ota.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 表单查询条件VO
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
@EqualsAndHashCode
@Builder
@Accessors(chain = true)
@Schema(title = "OtaUpgradesPageQuery", description = "OTA升级包")
public class OtaUpgradesPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "主键集合")
    private List<Long> ids;
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
     * 产品标识集合
     */
    @Schema(description = "产品标识集合")
    private List<String> productIdentifications;

    /**
     * 升级包版本号
     */
    @Schema(description = "升级包版本号")
    private String version;
    /**
     * 升级包的位置
     */
    @Schema(description = "升级包的位置")
    private String fileLocation;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;
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
    @Schema(description = "描述")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
