

package org.springblade.modules.iot.pojo.bridge.entity;

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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /** 配置ID */
    @TableField(value = "config_id")
    @AutoColumn(comment = "配置ID", defaultValueType = DefaultValueEnum.NULL)
    private Long configId;

    /** 配置名称 */
    @TableField(value = "config_name")
    @AutoColumn(comment = "配置名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String configName;

    /** 源系统 */
    @TableField(value = "source_system")
    @AutoColumn(comment = "源系统", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String sourceSystem;

    /** 消息数量 */
    @TableField(value = "message_count")
    @AutoColumn(comment = "消息数量", defaultValueType = DefaultValueEnum.NULL)
    private Integer messageCount;

    /** 成功数量 */
    @TableField(value = "success_count")
    @AutoColumn(comment = "成功数量", defaultValueType = DefaultValueEnum.NULL)
    private Integer successCount;

    /** 失败数量 */
    @TableField(value = "failed_count")
    @AutoColumn(comment = "失败数量", defaultValueType = DefaultValueEnum.NULL)
    private Integer failedCount;

    /** 错误信息 */
    @TableField(value = "error_message")
    @ColumnType("text")
    @AutoColumn(comment = "错误信息", defaultValueType = DefaultValueEnum.NULL)
    private String errorMessage;

    /** 执行时间(毫秒) */
    @TableField(value = "execution_time")
    @AutoColumn(comment = "执行时间(毫秒)", defaultValueType = DefaultValueEnum.NULL)
    private Long executionTime;

    /** 状态 */
    @TableField(value = "status")
    @AutoColumn(comment = "状态", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)



    /**
     * 状态枚举
     */
}
