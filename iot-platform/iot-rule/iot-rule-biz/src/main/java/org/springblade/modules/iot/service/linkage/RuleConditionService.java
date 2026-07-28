package org.springblade.modules.iot.service.linkage;

import com.obs.services.model.PolicyConditionItem;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.linkage.RuleCondition;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleConditionResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleConditionSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleConditionUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 规则条件表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:36:30
 * @create [2023-07-19 23:36:30] [mqttsnet]
 */
public interface RuleConditionService extends BaseService<RuleCondition> {

    /**
     * 保存规则条件表
     *
     * @param saveVO
     * @return
     */
    RuleCondition saveRuleCondition(RuleConditionSaveVO saveVO);

    /**
     * 修改规则条件表
     *
     * @param updateVO
     * @return
     */
    RuleCondition updateRuleCondition(RuleConditionUpdateVO updateVO);

    /**
     * 删除规则条件表
     *
     * @param id
     * @return
     */
    Boolean deleteRuleCondition(Long id);


    /**
     * Retrieve all available condition operators.
     *
     * @return A list of all condition operators.
     */
    List<PolicyConditionItem.ConditionOperator> getAllOperator();

    /**
     * Retrieve all available condition operator connectors.
     *
     * @return A list of all condition operator connectors.
     */
    List<PolicyConditionItem.ConditionOperator> getAllOperatorConnect();

    /**
     * Validate the provided conditions.
     *
     * @param condition List of conditions to validate.
     * @return `true` if validation is successful, otherwise an exception will be thrown.
     */
    boolean check(List<ConditionInfoDTO> condition);

    /**
     * Flatten the conditions to get only single conditions.
     *
     * @param conditionInfos List of condition info.
     * @return A list of flattened single conditions.
     */
    List<SingleConditionDTO> selectSingleCondition(List<ConditionInfoDTO> conditionInfos);

    /**
     * Extract variable parameters from the conditions.
     *
     * @param conditionInfos List of condition info.
     * @return A list of extracted variable parameters.
     */
    List<ConditionParamResult> selectVariableParam(List<ConditionInfoDTO> conditionInfos);

    /**
     * Determine if the provided conditions are of single condition structure.
     *
     * @param conditionInfos List of condition info.
     * @return `true` if it's a single condition, `false` otherwise.
     */
    boolean isSingleCondition(List<ConditionInfoDTO> conditionInfos);

    /**
     * Translate the provided conditions into a human-readable format.
     *
     * @param condition List of conditions.
     * @return A string representation of the conditions in a human-readable format.
     */
    String transfer(List<ConditionInfoDTO> condition);


    /**
     * Saves the rule condition action.
     *
     * @param saveVO Data object containing the rule conditions to save.
     * @return Returns the result of the saved rule condition action.
     */
    RuleConditionResultVO saveRuleConditionAction(RuleConditionSaveVO saveVO);

    /**
     * Updates the rule condition action.
     *
     * @param updateVO Data object containing the rule conditions to update.
     * @return Returns the result of the updated rule condition action.
     */
    RuleConditionResultVO updateRuleConditionAction(RuleConditionUpdateVO updateVO);

    /**
     * Retrieves a list of rule conditions based on the provided query criteria.
     *
     * @param query The query conditions encapsulated in {@link RuleConditionPageQuery}.
     * @return A list of {@link RuleConditionResultVO} entities that match the provided query.
     */
    List<RuleConditionResultVO> getRuleConditionList(RuleConditionPageQuery query);

}


