package org.springblade.modules.iot.ota.dto;

import cn.hutool.core.map.MapUtil;
import org.springblade.basic.interfaces.echo.EchoVO;
import lombok.*;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
/**
 * <p>
 * 表单查询方法返回值DTO
 * OTA升级任务
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class OtaUpgradeTasksResultDTO extends Entity implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 升级包ID，关联ota_upgrades表
     */
    private Long upgradeId;
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 升级模式
     */
    private Integer upgradeMethod;
    /**
     * 升级范围
     */
    private Integer upgradeScope;

    /**
     * 任务状态
     */
    private Integer taskStatus;
    /**
     * 计划执行开始时间
     */
    private LocalDateTime scheduledStartTime;
    /**
     * 计划执行结束时间
     */
    private LocalDateTime scheduledEndTime;

    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;
    /**
     * 当前重试次数
     */
    private Integer currentRetryCount;
    /**
     * 待升级的源版本号
     */
    private String sourceVersions;

    /**
     * APP确认升级
     */
    private Boolean appConfirmationRequired;

    /**
     * 升级速率(恒定速率升级，10-1000)
     */
    private Integer upgradeRate;

    /**
     * 重试间隔分钟数(默认为10分钟)
     */
    private Integer retryIntervalMinutes;

    /**
     * 设备升级超时时间(分钟)
     */
    private Integer deviceUpgradeTimeout;

    /**
     * 升级时间
     */
    private LocalDateTime upgradeTime;

    /**
     * 创建时间
     */

    /**
     * 最新重试时间
     */
    private LocalDateTime lastRetryTime;

    /**
     * 获取任务超时截止时间
     * 基于计划执行时间加上设备升级超时时间计算
     * 用于判断任务是否超时：当前时间是否晚于计划执行时间 + 设备升级超时时间
     *
     * @return 任务超时截止时间，如果计划执行时间或设备升级超时时间未设置则返回null
     */
    public LocalDateTime getTimeoutDeadline() {
        if (this.scheduledStartTime == null || this.deviceUpgradeTimeout == null) {
            return null;
        }
        return this.scheduledStartTime.plusMinutes(this.deviceUpgradeTimeout);
    }

    /**
     * 任务描述
     */
    private String description;
    /**
     * 描述
     */
    /**
     * 创建人组织
     */
    private Long createdOrgId;

    /**
     * 升级包信息
     */
    private OtaUpgradesResultDTO otaUpgradesResult;}