package org.springblade.modules.iot.manager.linkage.impl;

import cn.hutool.core.text.CharSequenceUtil;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.entity.linkage.RuleActionExecutionLog;
import org.springblade.modules.iot.manager.linkage.RuleActionExecutionLogManager;
import org.springblade.modules.iot.mapper.linkage.RuleActionExecutionLogMapper;
import org.springblade.modules.iot.vo.query.linkage.RuleActionExecutionLogPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 规则动作执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:54:41
 * @create [2024-12-02 18:54:41] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleActionExecutionLogManagerImpl extends SuperManagerImpl<RuleActionExecutionLogMapper, RuleActionExecutionLog> implements RuleActionExecutionLogManager {


    private final RuleActionExecutionLogMapper ruleActionExecutionLogMapper;


    /**
     * 获取规则动作执行日志列表
     *
     * @param query 查询条件 {@link RuleActionExecutionLogPageQuery}
     * @return 规则动作执行日志列表 {@link RuleActionExecutionLog}
     */
    @Override
    public List<RuleActionExecutionLog> getRuleActionExecutionLogList(RuleActionExecutionLogPageQuery query) {
        QueryWrap<RuleActionExecutionLog> queryWrap = new QueryWrap<>();

        queryWrap.lambda().eq(query.getRuleExecutionId() != null, RuleActionExecutionLog::getRuleExecutionId, query.getRuleExecutionId());
        queryWrap.lambda().eq(query.getActionType() != null, RuleActionExecutionLog::getActionType, query.getActionType());
        queryWrap.lambda().like(CharSequenceUtil.isNotBlank(query.getActionContent()), RuleActionExecutionLog::getActionContent, query.getActionContent());
        queryWrap.lambda().eq(query.getResult() != null, RuleActionExecutionLog::getResult, query.getResult());
        queryWrap.lambda().like(CharSequenceUtil.isNotBlank(query.getRemark()), RuleActionExecutionLog::getRemark, query.getRemark());

        return ruleActionExecutionLogMapper.selectList(queryWrap);
    }

}


