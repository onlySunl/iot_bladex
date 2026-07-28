package org.springblade.modules.iot.service.execution.event.executionlog;

import org.springblade.modules.iot.enumeration.linkage.ExecutionLogTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleExecutionLogEvent
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * 规则执行日志事件
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/12/2       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/12/2 14:49
 */
@Getter
@Setter
public class RuleExecutionLogEvent extends BaseExecutionLogEvent {

    /**
     * 规则标识
     */
    private String ruleIdentification;

    /**
     * 规则名称
     */
    private String ruleName;

    public RuleExecutionLogEvent(Object source, Long ruleExecutionId, String ruleIdentification, String ruleName, LocalDateTime startTime, LocalDateTime endTime, String extendParams, String remark) {
        super(source, ruleExecutionId, startTime, endTime, extendParams, remark, ExecutionLogTypeEnum.RULE);
        this.ruleIdentification = ruleIdentification;
        this.ruleName = ruleName;
    }
}
