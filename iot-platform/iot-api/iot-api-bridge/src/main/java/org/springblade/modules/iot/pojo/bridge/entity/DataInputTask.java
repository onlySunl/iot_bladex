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

package org.springblade.modules.iot.pojo.bridge.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
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

    /** 配置ID */
    @TableField("config_id")
    private Long configId;

    /** 任务名称 */
    @TableField("task_name")
    private String taskName;

    /** 任务类型 */
    @TableField("task_type")
    private TaskType taskType;

    /** Cron表达式 */
    @TableField("cron_expression")
    private String cronExpression;

    /** 最后执行时间 */
    @TableField("last_execution_time")
    private LocalDateTime lastExecutionTime;

    /** 下次执行时间 */
    @TableField("next_execution_time")
    private LocalDateTime nextExecutionTime;

    /** 状态 */
    @TableField("status")

    /** 错误信息 */
    @TableField("error_message")
    private String errorMessage;

    /** 创建时间 */

    /** 更新时间 */

    /** 创建者 */

    /** 更新者 */

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
