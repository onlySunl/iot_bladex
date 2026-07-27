package org.springblade.modules.iot.service.linkage;

import org.springblade.core.mp.service.BladeService;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.entity.linkage.RuleConditionAction;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionActionPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleConditionActionResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleConditionActionSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleConditionActionUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 规则条件动作表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:24:37
 * @create [2023-07-19 23:24:37] [mqttsnet]
 */
public interface RuleConditionActionService extends BladeService<RuleConditionAction> {

    /**
     * 保存规则条件动作表
     *
     * @param saveVO
     * @return
     */
    RuleConditionAction saveRuleConditionAction(RuleConditionActionSaveVO saveVO);

    /**
     * 修改规则条件动作表
     *
     * @param updateVO
     * @return
     */
    RuleConditionAction updateRuleConditionAction(RuleConditionActionUpdateVO updateVO);

    /**
     * 删除规则条件动作表
     *
     * @param id
     * @return
     */
    Boolean deleteRuleConditionAction(Long id);

    /**
     * Retrieves a list of rule condition actions based on the provided query criteria.
     *
     * @param query The query conditions encapsulated in {@link RuleConditionActionPageQuery}.
     * @return A list of {@link RuleConditionActionResultVO} entities that match the provided query.
     */
    List<RuleConditionActionResultVO> getRuleConditionActionList(RuleConditionActionPageQuery query);

}


