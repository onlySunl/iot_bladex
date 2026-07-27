package org.springblade.modules.iot.service.execution.event.action;

import org.springblade.modules.iot.dto.linkage.RuleConditionActionPolicyDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;

public class CommandActionEvent extends RuleConditionActionEvent {

    public CommandActionEvent(Object source, PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        super(source, policyContext, actionPolicyDTO);
    }
}
