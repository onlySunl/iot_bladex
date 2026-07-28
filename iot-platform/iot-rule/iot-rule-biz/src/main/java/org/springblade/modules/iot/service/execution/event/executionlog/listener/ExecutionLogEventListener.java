package org.springblade.modules.iot.service.execution.event.executionlog.listener;

import org.springblade.modules.iot.service.execution.event.executionlog.BaseExecutionLogEvent;
import org.springblade.modules.iot.service.linkage.RuleActionExecutionLogService;
import org.springblade.modules.iot.service.linkage.RuleConditionExecutionLogService;
import org.springblade.modules.iot.service.linkage.RuleExecutionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExecutionLogEventListener
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * 执行日志事件监听器，监听执行日志事件并将日志信息保存到数据库
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
 * @date 2024/12/2 19:39
 */
@Slf4j
@Component
public class ExecutionLogEventListener {

    @Autowired
    private RuleExecutionLogService ruleExecutionLogService;

    @Autowired
    private RuleConditionExecutionLogService ruleConditionExecutionLogService;

    @Autowired
    private RuleActionExecutionLogService ruleActionExecutionLogService;


    /**
     * 处理执行日志事件
     *
     * @param event 执行日志事件
     */
    @EventListener
    public void handleExecutionLogEvent(BaseExecutionLogEvent event) {
        log.info("Handling execution log event: {}", event);
        switch (event.getExecutionLogTypeEnum()) {
            case RULE:
                ruleExecutionLogService.saveRuleExecutionLog(event);
                break;
            case CONDITION:
                ruleConditionExecutionLogService.saveConditionExecutionLog(event);
                break;
            case ACTION:
                ruleActionExecutionLogService.saveActionExecutionLog(event);
                break;
            default:
                log.warn("Unsupported log type: {}", event.getExecutionLogTypeEnum());
        }
    }
}
