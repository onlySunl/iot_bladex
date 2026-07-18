
package org.springblade.modules.iot.temporal.kw.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.IRuleLogData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.kw.dao.KwRuleLogMapper;
import org.springblade.modules.iot.temporal.kw.model.KwRuleLog;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RuleLogDataImpl implements IRuleLogData {

    @Autowired
    private KwRuleLogMapper ruleLogMapper;

    @Override
    public void deleteByRuleId(Long ruleId) {
        ruleLogMapper.delete(KwRuleLog::getRuleId, ruleId);
    }

    @Override
    public PageResult<RuleLog> findByRuleId(Long ruleId, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        PageResult<KwRuleLog> result = ruleLogMapper.selectPage(pageParam,
                Wrappers.lambdaQuery(KwRuleLog.class).eq(KwRuleLog::getRuleId, ruleId).orderByDesc(KwRuleLog::getTime)
        );
        return new PageResult<>(result.getList().stream().map(r ->
                        new RuleLog(r.getTime().getTime(), ruleId, r.getState1(),
                                r.getContent(), r.getSuccess(), r.getTime().getTime()))
                .collect(Collectors.toList()), result.getTotal());
    }

    @Override
    public void add(RuleLog log) {
        KwRuleLog ruleLog = BeanUtil.copy(log, KwRuleLog.class);
        ruleLog.setState1(log.getState());
        ruleLog.setTime(new KWTimestamp(System.currentTimeMillis()));
        ruleLogMapper.insert(ruleLog);
    }
}
