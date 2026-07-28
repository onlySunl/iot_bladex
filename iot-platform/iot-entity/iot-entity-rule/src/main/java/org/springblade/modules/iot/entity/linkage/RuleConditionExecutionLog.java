package org.springblade.modules.iot.entity.linkage;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import org.springblade.basic.base.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * <p>
 * 实体类
 * 规则条件执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:53:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_condition_execution_log", comment = "RuleConditionExecutionLog table")
public class RuleConditionExecutionLog extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规则执行日志ID
     */
    
    @AutoColumn(value = "rule_execution_id", comment = "规则执行日志ID")
    private Long ruleExecutionId;
    /**
     * 条件唯一标识
     */
    
    @AutoColumn(value = "condition_uuid", comment = "条件唯一标识")
    private String conditionUuid;
    /**
     * 条件类型：0-设备属性触发，1-定时触发，2-设备动作触发等
     */
    
    @AutoColumn(value = "condition_type", comment = "条件类型：0-设备属性触发，1-定时触发，2-设备动作触发等")
    private Integer conditionType;
    /**
     * 条件是否成立
     */
    
    @AutoColumn(value = "evaluation_result", comment = "条件是否成立")
    private Boolean evaluationResult;
    /**
     * 条件评估开始时间
     */
    
    @AutoColumn(value = "start_time", comment = "条件评估开始时间")
    private LocalDateTime startTime;
    /**
     * 条件评估结束时间
     */
    
    @AutoColumn(value = "end_time", comment = "条件评估结束时间")
    private LocalDateTime endTime;
    /**
     * 扩展参数（文本格式）
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（文本格式）")
    private String extendParams;

    }
