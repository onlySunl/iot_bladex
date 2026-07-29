package org.springblade.modules.iot.entity.linkage;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;


/**
 * <p>
 * 实体类
 * 规则实例表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-05 23:04:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_instance", comment = "RuleInstance table")
public class RuleInstance extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 规则实例名称
     */
    
    @AutoColumn(value = "rule_name", comment = "规则实例名称")
    private String ruleName;
    /**
     * 流程ID， 规则实例类型为“规则编排”时，该项为对应的NedRed流程
     */
    
    @AutoColumn(value = "flow_id", comment = "流程ID， 规则实例类型为“规则编排”时，该项为对应的NedRed流程")
    private String flowId;
    /**
     * 流程数据
     */
    
    @AutoColumn(value = "flow_data", comment = "流程数据")
    private String flowData;
    /**
     * 规则实例类型(字典标识：RULE_INSTANCE_TYPE）
     */
    
    @AutoColumn(value = "type", comment = "规则实例类型(字典标识：RULE_INSTANCE_TYPE）")
    private Integer type;

    /**
     * 实例地址
     */
    
    @AutoColumn(value = "instance_address", comment = "实例地址")
    private String instanceAddress;
    }
