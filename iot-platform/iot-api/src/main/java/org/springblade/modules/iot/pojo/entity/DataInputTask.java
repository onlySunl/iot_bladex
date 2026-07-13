/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_data_input_task")
@Data
public class DataInputTask extends CustomBaseEntity {

    /** 配置ID */
    @TableField("config_id")
    private Long configId;

    /** 任务名称 */
    @TableField("task_name")
    private String taskName;

    /** 任务类型 */
    @TableField("task_type")
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)

    /** 错误信息 */
    @TableField("error_message")
    private String errorMessage;

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
