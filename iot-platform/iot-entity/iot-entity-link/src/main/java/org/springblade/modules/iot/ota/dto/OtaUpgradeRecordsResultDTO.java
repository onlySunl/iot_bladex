package org.springblade.modules.iot.ota.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.base.entity.Entity;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * <p>
 * 表单查询方法返回值DTO
 * OTA升级记录表
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
public class OtaUpgradeRecordsResultDTO extends CustomBaseEntity implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 升级包ID，关联ota_upgrades表
     */
    private Long upgradeId;

    /**
     * 任务ID，关联ota_upgrade_tasks表
     */
    private Long taskId;
    /**
     * 设备标识
     */
    private String deviceIdentification;
    /**
     * 升级状态(0:待升级、1:升级中、2:升级成功、3:升级失败)
     */
    private Integer upgradeStatus;

    /**
     * 待升级的源版本号
     */
    private String sourceVersion;

    /**
     * 目标版本号
     */
    private String targetVersion;

    /**
     * APP确认状态
     */
    private Integer appConfirmationStatus;

    /**
     * APP确认时间
     */
    private LocalDateTime appConfirmationTime;

    /**
     * 指令下发状态
     */
    private Integer commandSendStatus;

    /**
     * 最新指令下发时间
     */
    private LocalDateTime lastCommandSendTime;

    /**
     * OTA指令内容
     */
    private String commandContent;

    /**
     * 升级进度（百分比）
     */
    private Integer progress;
    /**
     * 错误代码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 升级开始时间
     */
    private LocalDateTime startTime;
    /**
     * 升级结束时间
     */
    private LocalDateTime endTime;
    /**
     * 升级成功详细信息
     */
    private String successDetails;
    /**
     * 升级失败详细信息
     */
    private String failureDetails;
    /**
     * 升级过程日志
     */
    private String logDetails;
    /**
     * 描述
     */
    /**
     * 创建人组织
     */
    private Long createdOrgId;}