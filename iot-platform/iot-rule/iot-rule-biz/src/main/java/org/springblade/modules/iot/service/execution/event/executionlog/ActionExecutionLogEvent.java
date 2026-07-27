package org.springblade.modules.iot.service.execution.event.executionlog;

import org.springblade.modules.iot.enumeration.linkage.ExecutionLogTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * -----------------------------------------------------------------------------
 * File Name: ActionExecutionLogEvent
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * 动作执行日志事件
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
 * @date 2024/12/2 14:50
 */
@Getter
@Setter
public class ActionExecutionLogEvent extends BaseExecutionLogEvent {

    private Integer actionType;
    private String actionContent;
    private Boolean result;

    public ActionExecutionLogEvent(Object source, Long ruleExecutionId, Integer actionType, String actionContent, Boolean result,
                                   LocalDateTime startTime, LocalDateTime endTime, String extendParams, String remark) {
        super(source, ruleExecutionId, startTime, endTime, extendParams, remark, ExecutionLogTypeEnum.ACTION);
        this.actionType = actionType;
        this.actionContent = actionContent;
        this.result = result;
    }
}

