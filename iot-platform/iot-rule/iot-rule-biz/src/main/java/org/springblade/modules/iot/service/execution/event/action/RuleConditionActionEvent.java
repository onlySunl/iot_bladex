package org.springblade.modules.iot.service.execution.event.action;

import org.springblade.modules.iot.dto.linkage.RuleConditionActionPolicyDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;
import org.springframework.context.ApplicationEvent;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleConditionActionEvent
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
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
 * @date 2023/12/17 20:28
 */
public abstract class RuleConditionActionEvent extends ApplicationEvent {
    private final PolicyContext policyContext;
    private final RuleConditionActionPolicyDTO actionPolicyDTO;

    public RuleConditionActionEvent(Object source, PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        super(source);
        this.policyContext = policyContext;
        this.actionPolicyDTO = actionPolicyDTO;
    }

    public RuleConditionActionPolicyDTO getActionPolicyDTO() {
        return actionPolicyDTO;
    }

    public PolicyContext getPolicyContext() {
        return policyContext;
    }


}

