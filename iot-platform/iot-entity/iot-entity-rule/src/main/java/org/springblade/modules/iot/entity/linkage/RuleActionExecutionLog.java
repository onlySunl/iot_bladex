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
 * 规则动作执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:54:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_action_execution_log", comment = "RuleActionExecutionLog table")
public class RuleActionExecutionLog extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规则执行日志ID（外键）
     */
    
    @AutoColumn(value = "rule_execution_id", comment = "规则执行日志ID（外键）")
    private Long ruleExecutionId;
    /**
     * 动作类型：0-命令下发，1-触发告警，2-数据转发
     */
    
    @AutoColumn(value = "action_type", comment = "动作类型：0-命令下发，1-触发告警，2-数据转发")
    private Integer actionType;
    /**
     * 动作内容
     */
    
    @AutoColumn(value = "action_content", comment = "动作内容")
    private String actionContent;
    /**
     * 动作是否执行成功
     */
    
    @AutoColumn(value = "result", comment = "动作是否执行成功")
    private Boolean result;
    /**
     * 动作开始执行时间
     */
    
    @AutoColumn(value = "start_time", comment = "动作开始执行时间")
    private LocalDateTime startTime;
    /**
     * 动作结束执行时间
     */
    
    @AutoColumn(value = "end_time", comment = "动作结束执行时间")
    private LocalDateTime endTime;
    /**
     * 扩展参数（文本格式）
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（文本格式）")
    private String extendParams;
    }
