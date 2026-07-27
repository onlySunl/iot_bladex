package org.springblade.modules.iot.entity.plugin;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_plugin_info", comment = "PluginInfo table")
public class PluginInfo extends CustomBaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID，所属应用场景
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID，所属应用场景")
    private String appId;
    /**
     * 插件唯一标识，自动生成：plugin_code + version
     */
    
    @AutoColumn(value = "plugin_identification", comment = "插件唯一标识，自动生成：plugin_code + version")
    private String pluginIdentification;
    /**
     * 插件代码标识，取自 pluginMeta.properties
     */
    
    @AutoColumn(value = "plugin_code", comment = "插件代码标识，取自 pluginMeta.properties")
    private String pluginCode;
    /**
     * 插件名称
     */
    
    @AutoColumn(value = "plugin_name", comment = "插件名称")
    private String pluginName;
    /**
     * 插件版本，取自 pluginMeta.properties
     */
    
    @AutoColumn(value = "version", comment = "插件版本，取自 pluginMeta.properties")
    private String version;
    /**
     * 插件描述，取自 pluginMeta.properties
     */
    
    @AutoColumn(value = "description", comment = "插件描述，取自 pluginMeta.properties")
    private String description;
    /**
     * 文件在服务器上的唯一标识，用于查询文件临时路径
     */
    
    @AutoColumn(value = "file_id", comment = "文件在服务器上的唯一标识，用于查询文件临时路径")
    private String fileId;
    /**
     * 文件大小（MB）
     */
    
    @AutoColumn(value = "file_size", comment = "文件大小（MB）")
    private BigDecimal fileSize;
    /**
     * 插件级别：0-系统级，1-用户级
     */
    
    @AutoColumn(value = "level", comment = "插件级别：0-系统级，1-用户级")
    private Integer level;
    /**
     * 插件类型：0-设备协议插件，1-业务插件
     */
    
    @AutoColumn(value = "type", comment = "插件类型：0-设备协议插件，1-业务插件")
    private Integer type;
    /**
     * 运行模式：0-单节点，1-集群
     */
    
    @AutoColumn(value = "run_mode", comment = "运行模式：0-单节点，1-集群")
    private Integer runMode;
    /**
     * 许可证类型（如GPL, MIT, 商业等）
     */
    
    @AutoColumn(value = "license_type", comment = "许可证类型（如GPL, MIT, 商业等）")
    private String licenseType;
    /**
     * 许可证密钥或证书
     */
    
    @AutoColumn(value = "license_key", comment = "许可证密钥或证书")
    private String licenseKey;
    /**
     * 许可证有效期
     */
    
    @AutoColumn(value = "valid_until", comment = "许可证有效期")
    private LocalDate validUntil;
    /**
     * 文件的哈希值，用于验证文件的完整性（如 SHA-256）
     */
    
    @AutoColumn(value = "file_hash", comment = "文件的哈希值，用于验证文件的完整性（如 SHA-256）")
    private String fileHash;
    /**
     * 扫描状态：PENDING, SUCCESS, FAILED
     */
    
    @AutoColumn(value = "scan_status", comment = "扫描状态：PENDING, SUCCESS, FAILED")
    private String scanStatus;
    /**
     * 扫描报告的文件ID
     */
    
    @AutoColumn(value = "scan_report_file_id", comment = "扫描报告的文件ID")
    private String scanReportFileId;
    /**
     * 最后一次扫描的日期
     */
    
    @AutoColumn(value = "scan_date", comment = "最后一次扫描的日期")
    private LocalDateTime scanDate;
    /**
     * 扫描摘要（如发现的漏洞数目等）
     */
    
    @AutoColumn(value = "scan_summary", comment = "扫描摘要（如发现的漏洞数目等）")
    private String scanSummary;
    /**
     * 扩展参数（预留）
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（预留）")
    private String extendParams;
    }
