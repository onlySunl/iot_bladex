package org.springblade.modules.iot.ota.entity;
import org.springblade.common.entity.CustomBaseEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ota_upgrade_records", comment = "OtaUpgradeRecords table")
public class OtaUpgradeRecords extends CustomBaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 升级包ID，关联ota_upgrades表
     */
    @AutoColumn(value = "upgrade_id", comment = "升级包ID，关联ota_upgrades表")
    private Long upgradeId;

    /**
     * 任务ID，关联ota_upgrade_tasks表
     */
    @AutoColumn(value = "task_id", comment = "任务ID，关联ota_upgrade_tasks表")
    private Long taskId;
    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;
    /**
     * 升级状态(0:待升级、1:升级中、2:升级成功、3:升级失败)
     */
    @AutoColumn(value = "upgrade_status", comment = "升级状态(0:待升级、1:升级中、2:升级成功、3:升级失败)")
    private Integer upgradeStatus;

    /**
     * 待升级的源版本号
     */
    @AutoColumn(value = "source_version", comment = "待升级的源版本号")
    private String sourceVersion;

    /**
     * 目标版本号
     */
    @AutoColumn(value = "target_version", comment = "目标版本号")
    private String targetVersion;

    /**
     * APP确认状态
     */
    @AutoColumn(value = "app_confirmation_status", comment = "APP确认状态")
    private Integer appConfirmationStatus;

    /**
     * APP确认时间
     */
    @AutoColumn(value = "app_confirmation_time", comment = "APP确认时间")
    private LocalDateTime appConfirmationTime;

    /**
     *指令下发状态
     */
    @AutoColumn(value = "command_send_status", comment = "指令下发状态")
    private Integer commandSendStatus;


    /**
     * 最新指令下发时间
     */
    @AutoColumn(value = "last_command_send_time", comment = "最新指令下发时间")
    private LocalDateTime lastCommandSendTime;

    /**
     *OTA指令内容
     */
    @AutoColumn(value = "command_content", comment = "OTA指令内容")
    private String commandContent;

    /**
     * 升级进度（百分比）
     */
    @AutoColumn(value = "progress", comment = "升级进度（百分比）")
    private Integer progress;
    /**
     * 错误代码
     */
    @AutoColumn(value = "error_code", comment = "错误代码")
    private String errorCode;
    /**
     * 错误信息
     */
    @AutoColumn(value = "error_message", comment = "错误信息")
    private String errorMessage;
    /**
     * 升级开始时间
     */
    @AutoColumn(value = "start_time", comment = "升级开始时间")
    private LocalDateTime startTime;
    /**
     * 升级结束时间
     */
    @AutoColumn(value = "end_time", comment = "升级结束时间")
    private LocalDateTime endTime;
    /**
     * 升级成功详细信息
     */
    @AutoColumn(value = "success_details", comment = "升级成功详细信息")
    private String successDetails;
    /**
     * 升级失败详细信息
     */
    @AutoColumn(value = "failure_details", comment = "升级失败详细信息")
    private String failureDetails;
    /**
     * 升级过程日志
     */
    @AutoColumn(value = "log_details", comment = "升级过程日志")
    private String logDetails;
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
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;

}
