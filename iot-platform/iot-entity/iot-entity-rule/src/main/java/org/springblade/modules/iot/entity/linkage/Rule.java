package org.springblade.modules.iot.entity.linkage;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;


/**
 * <p>
 * 实体类
 * 规则信息
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule", comment = "Rule table")
public class Rule extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    
    @AutoColumn(value = "rule_name", comment = "规则名称")
    private String ruleName;
    /**
     * 规则标识
     */
    
    @AutoColumn(value = "rule_identification", comment = "规则标识")
    private String ruleIdentification;
    /**
     * 应用ID
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 生效类型
     */
    
    @AutoColumn(value = "effective_type", comment = "生效类型")
    private Integer effectiveType;
    /**
     * 指定内容
     */
    
    @AutoColumn(value = "appoint_content", comment = "指定内容")
    private String appointContent;
    }
