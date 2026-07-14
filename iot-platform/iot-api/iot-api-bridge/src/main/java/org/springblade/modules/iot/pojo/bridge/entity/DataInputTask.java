

package org.springblade.modules.iot.pojo.bridge.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.common.enums.TaskType;

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

    /** 错误信息 */
    @TableField("error_message")
    private String errorMessage;

}
