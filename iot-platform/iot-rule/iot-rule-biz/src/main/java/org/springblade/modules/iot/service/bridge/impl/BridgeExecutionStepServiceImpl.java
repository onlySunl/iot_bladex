package org.springblade.modules.iot.service.bridge.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.basic.base.service.impl.SuperServiceImpl;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.modules.iot.entity.bridge.BridgeExecutionStep;
import org.springblade.modules.iot.manager.bridge.BridgeExecutionStepManager;
import org.springblade.modules.iot.service.bridge.BridgeExecutionStepService;
import org.springblade.modules.iot.vo.query.bridge.BridgeExecutionStepPageQuery;
import org.springblade.modules.iot.vo.result.bridge.BridgeExecutionStepResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 桥接执行步骤明细业务实现。
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class BridgeExecutionStepServiceImpl
        extends SuperServiceImpl<BridgeExecutionStepManager, Long, BridgeExecutionStep>
        implements BridgeExecutionStepService {

    @Override
    public List<BridgeExecutionStepResultVO> getStepResultVOList(BridgeExecutionStepPageQuery query) {
        return BeanPlusUtil.copyToList(superManager.getStepList(query), BridgeExecutionStepResultVO.class);
    }

    @Override
    public boolean removeBefore(LocalDateTime cutoff) {
        return superManager.remove(Wrappers.<BridgeExecutionStep>lambdaQuery()
                .lt(BridgeExecutionStep::getStartedAt, cutoff));
    }

    @Override
    public List<BridgeExecutionStep> getStepsByTraceId(String traceId) {
        return superManager.getStepsByTraceId(traceId);
    }

    @Override
    public List<BridgeExecutionStep> getStepsByTraceIdAndRuleId(String traceId, Long ruleId) {
        return superManager.getStepsByTraceIdAndRuleId(traceId, ruleId);
    }
}
