package org.springblade.modules.iot.ota.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 表单查询条件VO
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "OtaUpgradeRecordsPageQuery", description = "OTA升级记录查询参数")
public class OtaUpgradeRecordsPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 任务ID，关联ota_upgrade_tasks表
     */
    @Schema(description = "任务ID，关联ota_upgrade_tasks表")
    private Long taskId;
    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;

    /**
     * 设备标识集合
     */
    @Schema(description = "设备标识集合")
    private List<String> deviceIdentificationList;

    /**
     * 待升级的源版本号
     */
    @Schema(description = "待升级的源版本号")
    private String sourceVersion;

    /**
     * 目标版本号
     */
    @Schema(description = "目标版本号")
    private String targetVersion;

    /**
     * APP确认状态集合
     */
    @Schema(description = "APP确认状态集合")
    private List<Integer> appConfirmationStatusList;

    /**
     * 升级状态(0:待升级、1:升级中、2:升级成功、3:升级失败)
     */
    @Schema(description = "升级状态(0:待升级、1:升级中、2:升级成功、3:升级失败)")
    private Integer upgradeStatus;
    /**
     * 升级进度（百分比）
     */
    @Schema(description = "升级进度（百分比）")
    private Integer progress;
    /**
     * 错误代码
     */
    @Schema(description = "错误代码")
    private String errorCode;
    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private String errorMessage;
    /**
     * 升级开始时间
     */
    @Schema(description = "升级开始时间")
    private LocalDateTime startTime;
    /**
     * 升级结束时间
     */
    @Schema(description = "升级结束时间")
    private LocalDateTime endTime;
    /**
     * 升级成功详细信息
     */
    @Schema(description = "升级成功详细信息")
    private String successDetails;
    /**
     * 升级失败详细信息
     */
    @Schema(description = "升级失败详细信息")
    private String failureDetails;
    /**
     * 升级过程日志
     */
    @Schema(description = "升级过程日志")
    private String logDetails;
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
