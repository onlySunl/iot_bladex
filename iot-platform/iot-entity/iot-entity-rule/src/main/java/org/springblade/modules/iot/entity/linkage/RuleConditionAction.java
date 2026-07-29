package org.springblade.modules.iot.entity.linkage;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;


/**
 * <p>
 * 实体类
 * 规则条件动作表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:24:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_condition_action", comment = "RuleConditionAction table")
public class RuleConditionAction extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 规则条件ID
     */
    
    @AutoColumn(value = "rule_condition_id", comment = "规则条件ID")
    private Long ruleConditionId;
    /**
     * 执行动作
     */
    
    @AutoColumn(value = "action_type", comment = "执行动作")
    private Integer actionType;
    /**
     * 动作内容
     */
    
    @AutoColumn(value = "action_content", comment = "动作内容")
    private String actionContent;
    }
