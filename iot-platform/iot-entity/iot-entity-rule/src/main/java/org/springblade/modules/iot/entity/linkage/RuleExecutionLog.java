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

import java.io.Serial;
import java.time.LocalDateTime;


/**
 * <p>
 * 实体类
 * 规则执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:41:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_execution_log", comment = "RuleExecutionLog table")
public class RuleExecutionLog extends CustomBaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则标识
     */
    
    @AutoColumn(value = "rule_identification", comment = "规则标识")
    private String ruleIdentification;
    /**
     * 规则名称
     */
    
    @AutoColumn(value = "rule_name", comment = "规则名称")
    private String ruleName;
    /**
     * 规则执行开始时间
     */
    
    @AutoColumn(value = "start_time", comment = "规则执行开始时间")
    private LocalDateTime startTime;
    /**
     * 规则执行结束时间
     */
    
    @AutoColumn(value = "end_time", comment = "规则执行结束时间")
    private LocalDateTime endTime;
    /**
     * 扩展参数（文本格式）
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（文本格式）")
    private String extendParams;

    }
