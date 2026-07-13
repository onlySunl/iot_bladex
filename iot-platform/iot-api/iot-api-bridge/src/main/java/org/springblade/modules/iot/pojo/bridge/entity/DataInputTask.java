/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.databridge.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据输入任务实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_data_input_task")
public class DataInputTask extends CustomBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 配置ID */
    @TableField(value = "config_id")
    @AutoColumn(comment = "配置ID", defaultValueType = DefaultValueEnum.NULL)
    private Long configId;

    /** 任务名称 */
    @TableField(value = "task_name")
    @AutoColumn(comment = "任务名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String taskName;

    /** 任务类型 */
    @TableField(value = "task_type")
    @AutoColumn(comment = "任务类型", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    /** Cron表达式 */
    @TableField(value = "cron_expression")
    @AutoColumn(comment = "Cron表达式", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String cronExpression;

    /** 最后执行时间 */
    @TableField(value = "last_execution_time")
    @AutoColumn(comment = "最后执行时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime lastExecutionTime;

    /** 下次执行时间 */
    @TableField(value = "next_execution_time")
    @AutoColumn(comment = "下次执行时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime nextExecutionTime;

    /** 状态 */
    @TableField(value = "status")
    @AutoColumn(comment = "状态", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)
    private Status status;

    /** 错误信息 */
    @TableField(value = "error_message")
    @ColumnType("text")
    @AutoColumn(comment = "错误信息", defaultValueType = DefaultValueEnum.NULL)
    private String errorMessage;

    /** 创建时间 */
    @TableField(value = "create_time")
    @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime updateTime;

    /** 创建者 */
    @TableField(value = "create_by")
    @AutoColumn(comment = "创建者", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String createBy;

    /** 更新者 */
    @TableField(value = "update_by")
    @AutoColumn(comment = "更新者", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String updateBy;

    /**
     * 任务类型枚举
     */
    public enum TaskType {
        SCHEDULED,  // 定时任务
        REALTIME    // 实时任务
    }

    /**
     * 状态枚举
     */
    public enum Status {
        RUNNING,    // 运行中
        STOPPED,    // 已停止
        ERROR       // 错误状态
    }
}
