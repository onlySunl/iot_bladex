package org.springblade.modules.iot.manager.bridge.impl;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.entity.bridge.BridgeExecutionStep;
import org.springblade.modules.iot.manager.bridge.BridgeExecutionStepManager;
import org.springblade.modules.iot.mapper.bridge.BridgeExecutionStepMapper;
import org.springblade.modules.iot.vo.query.bridge.BridgeExecutionStepPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 桥接执行步骤明细
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BridgeExecutionStepManagerImpl extends SuperManagerImpl<BridgeExecutionStepMapper, BridgeExecutionStep> implements BridgeExecutionStepManager {

    private final BridgeExecutionStepMapper stepMapper;

    @Override
    public List<BridgeExecutionStep> getStepList(BridgeExecutionStepPageQuery query) {
        QueryWrap<BridgeExecutionStep> wrap = new QueryWrap<>();
        wrap.lambda()
                .eq(StrUtil.isNotBlank(query.getTraceId()), BridgeExecutionStep::getTraceId, query.getTraceId())
                .eq(StrUtil.isNotBlank(query.getStepType()), BridgeExecutionStep::getStepType, query.getStepType())
                .eq(StrUtil.isNotBlank(query.getStatus()), BridgeExecutionStep::getStatus, query.getStatus())
                .orderByAsc(BridgeExecutionStep::getStepNo);
        return stepMapper.selectList(wrap);
    }

    @Override
    public List<BridgeExecutionStep> getStepsByTraceId(String traceId) {
        if (StrUtil.isBlank(traceId)) {
            return Collections.emptyList();
        }
        QueryWrap<BridgeExecutionStep> wrap = new QueryWrap<>();
        wrap.lambda()
                .eq(BridgeExecutionStep::getTraceId, traceId)
                .orderByAsc(BridgeExecutionStep::getStepNo);
        return stepMapper.selectList(wrap);
    }

    @Override
    public List<BridgeExecutionStep> getStepsByTraceIdAndRuleId(String traceId, Long ruleId) {
        if (StrUtil.isBlank(traceId) || ruleId == null) {
            return Collections.emptyList();
        }
        QueryWrap<BridgeExecutionStep> wrap = new QueryWrap<>();
        wrap.lambda()
                .eq(BridgeExecutionStep::getTraceId, traceId)
                .eq(BridgeExecutionStep::getBridgeRuleId, ruleId)
                .orderByAsc(BridgeExecutionStep::getStepNo);
        return stepMapper.selectList(wrap);
    }
}
