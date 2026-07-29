package org.springblade.modules.iot.manager.linkage;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.entity.linkage.RuleCondition;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 规则条件表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:36:30
 * @create [2023-07-19 23:36:30] [mqttsnet]
 */
public interface RuleConditionManager extends SuperManager<RuleCondition> {

    /**
     * Finds a RuleCondition based on its condition identification.
     *
     * @param conditionIdentification The condition's identification string.
     * @return {@link RuleCondition} The rule condition associated with the given identification.
     */
    RuleCondition findOneByConditionIdentification(String conditionIdentification);


    /**
     * Retrieves a list of rule conditions based on the provided query criteria.
     *
     * @param query The query conditions encapsulated in {@link RuleConditionPageQuery}.
     * @return A list of {@link RuleCondition} entities that match the provided query.
     */
    List<RuleCondition> getRuleConditionList(RuleConditionPageQuery query);

}


