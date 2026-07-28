package org.springblade.modules.iot.manager.linkage;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.entity.linkage.RuleInstance;

/**
 * <p>
 * 通用业务接口
 * 规则实例表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-05 23:04:02
 * @create [2023-07-05 23:04:02] [mqttsnet]
 */
public interface RuleInstanceManager extends BladeService<RuleInstance> {


    /**
     * 根据flowId 查询实例信息
     *
     * @param flowId 流程id
     * @return {@link RuleInstance}
     */
    RuleInstance selectOneByFlowId(String flowId);

}


