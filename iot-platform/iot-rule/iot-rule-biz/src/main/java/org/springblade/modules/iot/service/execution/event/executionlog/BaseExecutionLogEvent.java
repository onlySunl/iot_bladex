package org.springblade.modules.iot.service.execution.event.executionlog;

import org.springblade.modules.iot.enumeration.linkage.ExecutionLogTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExecutionLogEvent
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * 执行日志事件
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/12/2       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/12/2 19:34
 */
@Getter
@Setter
public class BaseExecutionLogEvent extends ApplicationEvent {

    /**
     * 规则执行ID
     */
    private Long ruleExecutionId;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 扩展参数o
     */
    private String extendParams;
    /**
     * 描述
     */
    private String remark;

    private ExecutionLogTypeEnum executionLogTypeEnum;

    public BaseExecutionLogEvent(Object source, Long ruleExecutionId, LocalDateTime startTime, LocalDateTime endTime, String extendParams, String remark, ExecutionLogTypeEnum executionLogTypeEnum) {
        super(source);
        this.ruleExecutionId = ruleExecutionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extendParams = extendParams;
        this.remark = remark;
        this.executionLogTypeEnum = executionLogTypeEnum;
    }
}
