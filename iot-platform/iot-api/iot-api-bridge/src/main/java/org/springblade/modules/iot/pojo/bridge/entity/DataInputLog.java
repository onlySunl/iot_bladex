

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
 * 数据输入日志实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_data_input_log")
public class DataInputLog extends CustomBaseEntity {

    /** 配置ID */
    @TableField("config_id")
    private Long configId;

    /** 配置名称 */
    @TableField("config_name")
    private String configName;

    /** 源系统 */
    @TableField("source_system")
    private String sourceSystem;

    /** 消息数量 */
    @TableField("message_count")
    private Integer messageCount;

    /** 成功数量 */
    @TableField("success_count")
    private Integer successCount;

    /** 失败数量 */
    @TableField("failed_count")
    private Integer failedCount;

    /** 错误信息 */
    @TableField("error_message")
    private String errorMessage;

    /** 执行时间(毫秒) */
    @TableField("execution_time")
    private Long executionTime;

}
