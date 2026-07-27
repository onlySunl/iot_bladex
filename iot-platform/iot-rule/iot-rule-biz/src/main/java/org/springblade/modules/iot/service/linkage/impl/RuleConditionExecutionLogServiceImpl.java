package org.springblade.modules.iot.service.linkage.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.entity.linkage.RuleConditionExecutionLog;
import org.springblade.modules.iot.manager.linkage.RuleConditionExecutionLogManager;
import org.springblade.modules.iot.service.execution.event.executionlog.BaseExecutionLogEvent;
import org.springblade.modules.iot.service.execution.event.executionlog.ConditionExecutionLogEvent;
import org.springblade.modules.iot.service.linkage.RuleConditionExecutionLogService;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionExecutionLogPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleConditionExecutionLogResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 规则条件执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:53:47
 * @create [2024-12-02 18:53:47] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleConditionExecutionLogServiceImpl extends BaseServiceImpl<RuleConditionExecutionLogManager, Long, RuleConditionExecutionLog> implements RuleConditionExecutionLogService {


    @Override
    public void saveConditionExecutionLog(BaseExecutionLogEvent event) {
        RuleConditionExecutionLog conditionLog = new RuleConditionExecutionLog();
        conditionLog.setRuleExecutionId(event.getRuleExecutionId());
        conditionLog.setConditionUuid(((ConditionExecutionLogEvent) event).getConditionUuid());
        conditionLog.setConditionType(((ConditionExecutionLogEvent) event).getConditionTypeEnum().getValue());
        conditionLog.setEvaluationResult(((ConditionExecutionLogEvent) event).getEvaluationResult());
        conditionLog.setStartTime(event.getStartTime());
        conditionLog.setEndTime(event.getEndTime());
        conditionLog.setExtendParams(event.getExtendParams());
        conditionLog.setRemark(event.getRemark());
        superManager.save(conditionLog);
        log.info("Condition execution log saved: {}", conditionLog);
    }


    /**
     * 获取规则条件执行日志列表
     *
     * @param query 查询条件 {@link RuleConditionExecutionLogPageQuery}
     * @return 规则条件执行日志列表 {@link RuleConditionExecutionLogResultVO}
     */
    @Override
    public List<RuleConditionExecutionLogResultVO> getRuleConditionExecutionLogResultVOList(RuleConditionExecutionLogPageQuery query) {
        List<RuleConditionExecutionLog> logs = superManager.getRuleConditionExecutionLogList(query);
        return BeanPlusUtil.toBeanList(logs, RuleConditionExecutionLogResultVO.class);
    }

    @Override
    public boolean removeByRuleExecutionIds(Collection<Long> ruleExecutionIds) {
        if (ruleExecutionIds == null || ruleExecutionIds.isEmpty()) {
            return false;
        }
        return superManager.remove(Wrappers.<RuleConditionExecutionLog>lambdaQuery()
                .in(RuleConditionExecutionLog::getRuleExecutionId, ruleExecutionIds));
    }
}

