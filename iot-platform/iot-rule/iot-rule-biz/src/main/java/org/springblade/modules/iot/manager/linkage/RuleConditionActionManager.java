package org.springblade.modules.iot.manager.linkage;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.entity.linkage.RuleConditionAction;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionActionPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 规则条件动作表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:24:37
 * @create [2023-07-19 23:24:37] [mqttsnet]
 */
public interface RuleConditionActionManager extends SuperManager<RuleConditionAction> {

    /**
     * Retrieves a list of rule condition actions based on the provided query criteria.
     *
     * @param query The query conditions encapsulated in {@link RuleConditionActionPageQuery}.
     * @return A list of {@link RuleConditionAction} entities that match the provided query.
     */
    List<RuleConditionAction> getRuleConditionActionList(RuleConditionActionPageQuery query);

}


