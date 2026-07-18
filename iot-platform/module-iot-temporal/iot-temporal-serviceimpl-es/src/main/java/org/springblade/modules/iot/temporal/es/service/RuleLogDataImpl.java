
package org.springblade.modules.iot.temporal.es.service;


import org.springblade.modules.iot.IRuleLogData;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.es.dao.RuleLogRepository;
import org.springblade.modules.iot.temporal.es.convert.EsRuleLogConvert;
import org.springblade.modules.iot.temporal.es.document.DocRuleLog;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RuleLogDataImpl implements IRuleLogData {

    @Autowired
    private RuleLogRepository ruleLogRepository;

    @Override
    public void deleteByRuleId(Long ruleId) {
        ruleLogRepository.deleteByRuleId(ruleId);
    }

    @Override
    public PageResult<RuleLog> findByRuleId(Long ruleId, int page, int size) {
        Page<DocRuleLog> paged = ruleLogRepository.findByRuleIdOrderByLogAtDesc(ruleId, Pageable.ofSize(size).withPage(page - 1));
        return new PageResult<>(
                paged.getContent().stream().map(o -> EsRuleLogConvert.INSTANCE.convert(o))
                        .collect(Collectors.toList()),paged.getTotalElements());
    }

    @Override
    public void add(RuleLog log) {
        ruleLogRepository.save(EsRuleLogConvert.INSTANCE.convertDoc(log));
    }
}
