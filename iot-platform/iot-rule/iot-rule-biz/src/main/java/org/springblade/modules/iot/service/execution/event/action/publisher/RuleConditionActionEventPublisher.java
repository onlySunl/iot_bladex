package org.springblade.modules.iot.service.execution.event.action.publisher;

import org.springblade.modules.iot.dto.linkage.RuleConditionActionPolicyDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;
import org.springblade.modules.iot.service.execution.event.action.AlertActionEvent;
import org.springblade.modules.iot.service.execution.event.action.CommandActionEvent;
import org.springblade.modules.iot.service.execution.event.action.ForwardActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleConditionActionEventPublisher
 * -----------------------------------------------------------------------------
 * Description:
 * 规则条件动作事件发布者
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2023/12/17       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2023/12/17 20:14
 */
@Component
@Slf4j
public class RuleConditionActionEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishCommandActionEvent(PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        log.info("Publishing command action event: {}", actionPolicyDTO.getActionContent());
        eventPublisher.publishEvent(new CommandActionEvent(this, policyContext, actionPolicyDTO));
    }

    public void publishAlertActionEvent(PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        log.info("Publishing alert action event: {}", actionPolicyDTO.getActionContent());
        eventPublisher.publishEvent(new AlertActionEvent(this, policyContext, actionPolicyDTO));
    }

    public void publishForwardActionEvent(PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        log.info("Publishing forward action event: {}", actionPolicyDTO.getActionContent());
        eventPublisher.publishEvent(new ForwardActionEvent(this, policyContext, actionPolicyDTO));
    }
}
