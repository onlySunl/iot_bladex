package org.springblade.modules.iot.vo.update.plugin;

import org.springblade.basic.base.entity.CustomBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单修改方法VO
 * 插件信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-25 19:05:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "插件信息表")
public class PluginInfoUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 应用ID，所属应用场景
     */
    @Schema(description = "应用ID，所属应用场景")
    @NotEmpty(message = "请填写应用ID，所属应用场景")
    @Size(max = 64, message = "应用ID，所属应用场景长度不能超过{max}")
    private String appId;

    /**
     * 插件名称
     */
    @Schema(description = "插件名称")
    @NotEmpty(message = "请填写插件名称")
    @Size(max = 255, message = "插件名称长度不能超过{max}")
    private String pluginName;

    /**
     * 文件在服务器上的唯一标识，用于查询文件临时路径
     */
    @Schema(description = "文件在服务器上的唯一标识，用于查询文件临时路径")
    @NotEmpty(message = "请填写文件在服务器上的唯一标识，用于查询文件临时路径")
    @Size(max = 255, message = "文件在服务器上的唯一标识，用于查询文件临时路径长度不能超过{max}")
    private String fileId;
    /**
     * 文件大小（MB）
     */
    @Schema(description = "文件大小（MB）")
    @NotNull(message = "请填写文件大小（MB）")
    private BigDecimal fileSize;
    /**
     * 插件级别：0-系统级，1-用户级
     */
    @Schema(description = "插件级别：0-系统级，1-用户级")
    @NotNull(message = "请填写插件级别：0-系统级，1-用户级")
    private Integer level;
    /**
     * 插件类型：0-设备协议插件，1-业务插件
     */
    @Schema(description = "插件类型：0-设备协议插件，1-业务插件")
    @NotNull(message = "请填写插件类型：0-设备协议插件，1-业务插件")
    private Integer type;
    /**
     * 运行模式：0-单节点，1-集群
     */
    @Schema(description = "运行模式：0-单节点，1-集群")
    @NotNull(message = "请填写运行模式：0-单节点，1-集群")
    private Integer runMode;
    /**
     * 许可证类型（如GPL, MIT, 商业等）
     */
    @Schema(description = "许可证类型（如GPL, MIT, 商业等）")
    @Size(max = 50, message = "许可证类型（如GPL, MIT, 商业等）长度不能超过{max}")
    private String licenseType;
    /**
     * 许可证密钥或证书
     */
    @Schema(description = "许可证密钥或证书")
    @Size(max = 255, message = "许可证密钥或证书长度不能超过{max}")
    private String licenseKey;
    /**
     * 许可证有效期
     */
    @Schema(description = "许可证有效期")
    private LocalDate validUntil;
    /**
     * 文件的哈希值，用于验证文件的完整性（如 SHA-256）
     */
    @Schema(description = "文件的哈希值，用于验证文件的完整性（如 SHA-256）")
    @Size(max = 255, message = "文件的哈希值，用于验证文件的完整性（如 SHA-256）长度不能超过{max}")
    private String fileHash;
    /**
     * 扫描状态：PENDING, SUCCESS, FAILED
     */
    @Schema(description = "扫描状态：PENDING, SUCCESS, FAILED")
    @Size(max = 50, message = "扫描状态：PENDING, SUCCESS, FAILED长度不能超过{max}")
    private String scanStatus;
    /**
     * 扫描报告的文件ID
     */
    @Schema(description = "扫描报告的文件ID")
    @Size(max = 255, message = "扫描报告的文件ID长度不能超过{max}")
    private String scanReportFileId;
    /**
     * 最后一次扫描的日期
     */
    @Schema(description = "最后一次扫描的日期")
    private LocalDateTime scanDate;
    /**
     * 扫描摘要（如发现的漏洞数目等）
     */
    @Schema(description = "扫描摘要（如发现的漏洞数目等）")
    @Size(max = 65535, message = "扫描摘要（如发现的漏洞数目等）长度不能超过{max}")
    private String scanSummary;
    /**
     * 扩展参数（预留）
     */
    @Schema(description = "扩展参数（预留）")
    @Size(max = 65535, message = "扩展参数（预留）长度不能超过{max}")
    private String extendParams;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
