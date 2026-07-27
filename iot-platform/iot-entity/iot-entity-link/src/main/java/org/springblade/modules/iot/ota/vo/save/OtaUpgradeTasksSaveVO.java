package org.springblade.modules.iot.ota.vo.save;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
/**
 * <p>
 * 表单保存方法VO
 * OTA升级任务表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "OtaUpgradeTasksSaveVO", description = "OTA升级任务表")
public class OtaUpgradeTasksSaveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 升级包ID，关联ota_upgrades表
     */
    @Schema(description = "升级包ID，关联ota_upgrades表")
    @NotNull(message = "请填写升级包ID，关联ota_upgrades表")
    private Long upgradeId;
    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    @NotEmpty(message = "请填写任务名称")
    @Size(max = 100, message = "任务名称长度不能超过{max}")
    private String taskName;

    /**
     * 升级模式
     */
    @Schema(description = "升级模式")
    @NotNull(message = "请填写升级模式")
    private Integer upgradeMethod;

    /**
     * 升级范围
     */
    @Schema(description = "升级范围")
    @NotNull(message = "请填写升级范围")
    private Integer upgradeScope;

    /**
     * 目标值
     * 升级目标值，根据升级范围不同，含义不同：
     * 升级范围为全部设备时，目标值为产品标识
     * 升级范围为定向升级时，目标值为设备唯一标识
     * 升级范围为分组升级时，目标值为分组ID列表
     * 升级范围为区域升级时，目标值为区域编码列表
     */
    @Schema(description = "目标值")
    @NotNull(message = "请填写目标值")
    @Size(max = 2000, message = "目标值长度不能超过{max}")
    private List<String> targetValueList;

    /**
     * 计划执行开始时间
     */
    @NotNull(message = "请填写计划执行开始时间")
    @Schema(description = "计划执行开始时间")
    private LocalDateTime scheduledStartTime;
    /**
     * 计划执行结束时间
     */
    @NotNull(message = "请填写计划执行结束时间")
    @Schema(description = "计划执行结束时间")
    private LocalDateTime scheduledEndTime;

    /**
     * 最大重试次数
     */
    @Schema(description = "最大重试次数")
    private Integer maxRetryCount;

    /**
     * 待升级的源版本号
     */
    @Schema(description = "待升级的源版本号")
    private String sourceVersions;

    /**
     * APP确认升级
     */
    @NotNull(message = "请填写APP确认升级")
    @Schema(description = "APP确认升级")
    private Boolean appConfirmationRequired;

     /**
     * 升级速率(恒定速率升级，10-1000)
     */
    @NotNull(message = "请填写升级速率(恒定速率升级，10-1000)")
    @Schema(description = "升级速率(恒定速率升级，10-1000)")
    private Integer upgradeRate;

    /**
     * 重试间隔分钟数(默认为10分钟)
     */
    @NotNull(message = "请填写重试间隔分钟数(默认为10分钟)")
    @Schema(description = "重试间隔分钟数(默认为10分钟)")
    private Integer retryIntervalMinutes;

    /**
     * 设备升级超时时间(分钟)
     */
    @Schema(description = "设备升级超时时间(分钟)")
    private Integer deviceUpgradeTimeout;


    /**
     * 任务描述
     */
    @NotEmpty(message = "请填写任务描述")
    @Schema(description = "任务描述")
    @Size(max = 255, message = "任务描述长度不能超过{max}")
    private String description;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}