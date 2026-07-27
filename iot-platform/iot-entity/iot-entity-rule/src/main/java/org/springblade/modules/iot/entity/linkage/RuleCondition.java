package org.springblade.modules.iot.entity.linkage;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * <p>
 * 实体类 规则条件表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:36:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_condition", comment = "RuleCondition table")
public class RuleCondition extends CustomBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 规则
     */
    
    @AutoColumn(value = "rule_id", comment = "规则")
    private Long ruleId;
    /**
     * 条件编码
     */
    
    @AutoColumn(value = "condition_identification", comment = "条件编码")
    private String conditionIdentification;
    /**
     * 条件类型
     */
    
    @AutoColumn(value = "condition_type", comment = "条件类型")
    private Integer conditionType;
    /**
     * 条件内容
     */
    
    @AutoColumn(value = "condition_scheme", comment = "条件内容")
    private String conditionScheme;
    /**
     * 防抖状态
     */
    
    @AutoColumn(value = "anti_shake", comment = "防抖状态")
    private Integer antiShake;
    /**
     * 防抖策略
     */
    
    @AutoColumn(value = "anti_shake_scheme", comment = "防抖策略")
    private String antiShakeScheme;
    }
