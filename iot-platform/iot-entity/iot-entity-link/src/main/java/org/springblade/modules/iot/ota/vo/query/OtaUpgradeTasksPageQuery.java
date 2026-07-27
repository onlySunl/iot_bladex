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
@Schema(title = "OtaUpgradeTasksPageQuery", description = "OTA升级任务表")
public class OtaUpgradeTasksPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 升级包ID，关联ota_upgrades表
     */
    @Schema(description = "升级包ID，关联ota_upgrades表")
    private Long upgradeId;

    /**
     * 升级包ID集合，关联ota_upgrades表
     */
    @Schema(description = "升级包ID集合，关联ota_upgrades表")
    private List<Long> upgradeIdList;
    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String taskName;
    /**
     * 任务状态(0:待发布、1:进行中、2:已完成、3:已取消)
     */
    @Schema(description = "任务状态(0:待发布、1:进行中、2:已完成、3:已取消)")
    private Integer taskStatus;

    /**
     * 任务状态集合(0:待发布、1:进行中、2:已完成、3:已取消)
     */
    @Schema(description = "任务状态集合(0:待发布、1:进行中、2:已完成、3:已取消)")
    private List<Integer> taskStatusList;

    /**
     * 计划执行开始时间
     */
    @Schema(description = "计划执行开始时间")
    private LocalDateTime scheduledStartTime;

    /**
     * 计划执行结束时间
     */
    @Schema(description = "计划执行结束时间")
    private LocalDateTime scheduledEndTime;

    /**
     * 计划执行开始时间范围查询-开始时间
     */
    @Schema(description = "计划执行开始时间范围查询-开始时间")
    private LocalDateTime scheduledStartTimeStart;

    /**
     * 计划执行开始时间范围查询-结束时间
     */
    @Schema(description = "计划执行开始时间范围查询-结束时间")
    private LocalDateTime scheduledStartTimeEnd;

    /**
     * 待升级的源版本号
     */
    @Schema(description = "待升级的源版本号")
    private String sourceVersions;

    /**
     * APP确认升级
     */
    @Schema(description = "APP确认升级")
    private Boolean appConfirmationRequired;

    /**
     * 任务描述
     */
    @Schema(description = "任务描述")
    private String description;
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