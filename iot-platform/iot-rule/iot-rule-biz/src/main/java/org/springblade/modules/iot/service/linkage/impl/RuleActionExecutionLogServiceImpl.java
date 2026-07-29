package org.springblade.modules.iot.service.linkage.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.basic.base.service.impl.SuperServiceImpl;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.modules.iot.entity.linkage.RuleActionExecutionLog;
import org.springblade.modules.iot.manager.linkage.RuleActionExecutionLogManager;
import org.springblade.modules.iot.service.execution.event.executionlog.ActionExecutionLogEvent;
import org.springblade.modules.iot.service.execution.event.executionlog.BaseExecutionLogEvent;
import org.springblade.modules.iot.service.linkage.RuleActionExecutionLogService;
import org.springblade.modules.iot.vo.query.linkage.RuleActionExecutionLogPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleActionExecutionLogResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 规则动作执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:54:41
 * @create [2024-12-02 18:54:41] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleActionExecutionLogServiceImpl extends SuperServiceImpl<RuleActionExecutionLogManager, Long, RuleActionExecutionLog> implements RuleActionExecutionLogService {


    @Override
    public void saveActionExecutionLog(BaseExecutionLogEvent event) {
        RuleActionExecutionLog actionLog = new RuleActionExecutionLog();
        actionLog.setRuleExecutionId(event.getRuleExecutionId());
        actionLog.setActionType(((ActionExecutionLogEvent) event).getActionType());
        actionLog.setActionContent(((ActionExecutionLogEvent) event).getActionContent());
        actionLog.setResult(((ActionExecutionLogEvent) event).getResult());
        actionLog.setStartTime(event.getStartTime());
        actionLog.setEndTime(event.getEndTime());
        actionLog.setExtendParams(event.getExtendParams());
        actionLog.setRemark(event.getRemark());
        superManager.save(actionLog);
        log.info("Action execution log saved: {}", actionLog);
    }


    /**
     * 获取规则动作执行日志列表
     *
     * @param query 查询条件 {@link RuleActionExecutionLogPageQuery}
     * @return 规则动作执行日志列表 {@link RuleActionExecutionLogResultVO}
     */
    @Override
    public List<RuleActionExecutionLogResultVO> getRuleActionExecutionLogResultVOList(RuleActionExecutionLogPageQuery query) {
        List<RuleActionExecutionLog> logs = superManager.getRuleActionExecutionLogList(query);
        return BeanPlusUtil.toBeanList(logs, RuleActionExecutionLogResultVO.class);
    }

    @Override
    public boolean removeByRuleExecutionIds(Collection<Long> ruleExecutionIds) {
        if (ruleExecutionIds == null || ruleExecutionIds.isEmpty()) {
            return false;
        }
        return superManager.remove(Wrappers.<RuleActionExecutionLog>lambdaQuery()
                .in(RuleActionExecutionLog::getRuleExecutionId, ruleExecutionIds));
    }
}

