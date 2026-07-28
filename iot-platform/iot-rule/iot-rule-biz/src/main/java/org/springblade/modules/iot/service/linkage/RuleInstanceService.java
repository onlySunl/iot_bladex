package org.springblade.modules.iot.service.linkage;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.entity.linkage.RuleInstance;
import org.springblade.modules.iot.vo.result.linkage.RuleInstanceResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleInstanceSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleInstanceFlowUpdateVO;
import org.springblade.modules.iot.vo.update.linkage.RuleInstanceUpdateVO;

/**
 * <p>
 * 业务接口
 * 规则实例表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-05 23:04:02
 * @create [2023-07-05 23:04:02] [mqttsnet]
 */
public interface RuleInstanceService extends BladeService<Long, RuleInstance> {

    /**
     * 保存规则实例信息
     *
     * @param saveVO
     * @return {@link RuleInstanceResultVO}
     */
    RuleInstanceResultVO saveRuleInstance(RuleInstanceSaveVO saveVO);

    /**
     * 修改规则实例信息
     *
     * @param updateVO
     * @return {@link RuleInstanceResultVO}
     */
    RuleInstanceResultVO updateRuleInstance(RuleInstanceUpdateVO updateVO);

    /**
     * 删除规则实例信息
     *
     * @param id
     * @return
     */
    Boolean deleteRuleInstance(Long id);

    /**
     * 根据规则实例ID更新规则状态
     *
     * @param id     规则实例ID
     * @param status 规则实例状态
     * @return {@link Boolean} 更新结果
     */
    Boolean updateRuleInstanceStatus(Long id, Integer status);


    /**
     * 更新流程数据
     *
     * @param updateVO
     * @return
     */
    RuleInstanceResultVO updateRuleInstanceFlowData(RuleInstanceFlowUpdateVO updateVO);


    /**
     * Fetches detailed information about a rule instance using its flow ID.
     *
     * @param flowId The flow ID associated with the rule instance.
     * @return {@link RuleInstanceResultVO} Detailed information about the rule instance.
     */
    RuleInstanceResultVO getDetailsByFlowId(String flowId);

}


