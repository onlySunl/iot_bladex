package org.springblade.modules.iot.manager.linkage.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.common.constant.DsConstant;
import org.springblade.modules.iot.entity.linkage.RuleInstance;
import org.springblade.modules.iot.manager.linkage.RuleInstanceManager;
import org.springblade.modules.iot.mapper.linkage.RuleInstanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 规则实例表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-05 23:04:02
 * @create [2023-07-05 23:04:02] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@DS(DsConstant.BASE_TENANT)
public class RuleInstanceManagerImpl extends BladeServiceImpl<RuleInstanceMapper, RuleInstance> implements RuleInstanceManager {

    private final RuleInstanceMapper ruleInstanceMapper;

    @Override
    public RuleInstance selectOneByFlowId(String flowId) {
        QueryWrap<RuleInstance> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(flowId), RuleInstance::getFlowId, flowId);
        return ruleInstanceMapper.selectOne(queryWrap);
    }
}


