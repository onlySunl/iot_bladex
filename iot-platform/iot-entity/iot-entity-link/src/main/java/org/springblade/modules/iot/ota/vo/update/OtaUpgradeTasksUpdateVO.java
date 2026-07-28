package org.springblade.modules.iot.ota.vo.update;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springblade.basic.base.entity.Entity;
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
 * 表单修改方法VO
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
@Schema(title = "OtaUpgradeTasksUpdateVO", description = "OTA升级任务表")
public class OtaUpgradeTasksUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = Entity.Update.class)
    private Long id;

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
    @NotNull(message = "请填写最大重试次数")
    @Schema(description = "最大重试次数")
    private Integer maxRetryCount;
    
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