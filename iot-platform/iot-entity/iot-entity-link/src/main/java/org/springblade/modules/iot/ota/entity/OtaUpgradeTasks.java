package org.springblade.modules.iot.ota.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ota_upgrade_tasks", comment = "OtaUpgradeTasks table")
public class OtaUpgradeTasks extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 升级包ID，关联ota_upgrades表
     */
    @AutoColumn(value = "upgrade_id", comment = "升级包ID，关联ota_upgrades表")
    private Long upgradeId;
    /**
     * 任务名称
     */
    @AutoColumn(value = "task_name", comment = "任务名称")
    private String taskName;

     /**
     * 升级模式
     */
    @AutoColumn(value = "upgrade_method", comment = "升级模式")
    private Integer upgradeMethod;
    /**
     * 升级范围
     */
    @AutoColumn(value = "upgrade_scope", comment = "升级范围")
    private Integer upgradeScope;

    /**
     * 任务状态(0:待发布、1:进行中、2:已完成、3:已取消)
     */
    @AutoColumn(value = "task_status", comment = "任务状态(0:待发布、1:进行中、2:已完成、3:已取消)")
    private Integer taskStatus;
    /**
     * 计划执行开始时间
     */
    @AutoColumn(value = "scheduled_start_time", comment = "计划执行开始时间")
    private LocalDateTime scheduledStartTime;
    /**
     * 计划执行结束时间
     */
    @AutoColumn(value = "scheduled_end_time", comment = "计划执行结束时间")
    private LocalDateTime scheduledEndTime;
    /**
     * 最大重试次数
     */
    @AutoColumn(value = "max_retry_count", comment = "最大重试次数")
    private Integer maxRetryCount;

    /**
     * 当前重试次数
     */
    @AutoColumn(value = "current_retry_count", comment = "当前重试次数")
    private Integer currentRetryCount;

    /**
     * 待升级的源版本号
     */
    @AutoColumn(value = "source_versions", comment = "待升级的源版本号")
    private String sourceVersions;

    /**
     * APP确认升级
     */
    @AutoColumn(value = "app_confirmation_required", comment = "APP确认升级")
    private Boolean appConfirmationRequired;

    /**
     * 升级速率(恒定速率升级，10-1000)
     */
    @AutoColumn(value = "upgrade_rate", comment = "升级速率(恒定速率升级，10-1000)")
    private Integer upgradeRate;

    /**
     * 重试间隔分钟数(默认为10分钟)
     */
    @AutoColumn(value = "retry_interval_minutes", comment = "重试间隔分钟数(默认为10分钟)")
    private Integer retryIntervalMinutes;

     /**
     * 设备升级超时时间(分钟)
     */
    @AutoColumn(value = "device_upgrade_timeout", comment = "设备升级超时时间(分钟)")
    private Integer deviceUpgradeTimeout;

    /**
     * 最新重试时间
     */
    @AutoColumn(value = "last_retry_time", comment = "最新重试时间")
    private LocalDateTime lastRetryTime;

    /**
     * 任务描述
     */
    @AutoColumn(value = "description", comment = "任务描述")
    private String description;
    /**
     * 描述
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;

}