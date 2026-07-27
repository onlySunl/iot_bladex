package org.springblade.modules.iot.service.execution.event.action;

import org.springblade.modules.iot.dto.linkage.RuleConditionActionPolicyDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;

/**
 * -----------------------------------------------------------------------------
 * File Name: ForwardActionEvent
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
 * @date 2023/12/17 20:30
 */
public class ForwardActionEvent extends RuleConditionActionEvent {
    public ForwardActionEvent(Object source, PolicyContext policyContext, RuleConditionActionPolicyDTO actionPolicyDTO) {
        super(source, policyContext, actionPolicyDTO);
    }
}

