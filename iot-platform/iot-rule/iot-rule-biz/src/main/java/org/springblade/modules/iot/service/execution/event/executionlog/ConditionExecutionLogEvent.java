package org.springblade.modules.iot.service.execution.event.executionlog;

import org.springblade.modules.iot.enumeration.linkage.ConditionTypeEnum;
import org.springblade.modules.iot.enumeration.linkage.ExecutionLogTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * -----------------------------------------------------------------------------
 * File Name: ConditionExecutionLogEvent
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * 条件执行日志事件
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
public class ConditionExecutionLogEvent extends BaseExecutionLogEvent {

    /**
     * 条件UUID
     */
    private String conditionUuid;

    /**
     * 条件类型(ConditionTypeEnum)
     */
    private ConditionTypeEnum conditionTypeEnum;

    /**
     * 条件是否成立
     */
    private Boolean evaluationResult;

    public ConditionExecutionLogEvent(Object source, Long ruleExecutionId, String conditionUuid, ConditionTypeEnum conditionTypeEnum, Boolean evaluationResult, LocalDateTime startTime,
                                      LocalDateTime endTime, String extendParams, String remark) {
        super(source, ruleExecutionId, startTime, endTime, extendParams, remark, ExecutionLogTypeEnum.CONDITION);
        this.conditionUuid = conditionUuid;
        this.conditionTypeEnum = conditionTypeEnum;
        this.evaluationResult = evaluationResult;
    }
}