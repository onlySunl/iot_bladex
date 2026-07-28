package org.springblade.modules.iot.rule.facade.impl;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.rule.api.RuleJobHandlerApi;
import org.springblade.modules.iot.rule.facade.RuleJobHandlerFacade;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.linkage.RuleDetailsResultVO;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/12/24 21:07
 */
@Slf4j
@Service
public class RuleJobHandlerFacadeImpl implements RuleJobHandlerFacade {

    @Autowired
    @Lazy
    private RuleJobHandlerApi ruleJobHandlerApi;

    @Override
    public R<RuleDetailsResultVO> triggerRulePolicy(Long tenantId, String ruleIdentification) {
        return ruleJobHandlerApi.triggerRulePolicy(tenantId, ruleIdentification);
    }

    @Override
    public R<Boolean> flushGroovyScriptCache() {
        return ruleJobHandlerApi.flushGroovyScriptCache();
    }

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScript(RuleGroovyScriptExecuteScriptParam param) {
        return ruleJobHandlerApi.executeScript(param);
    }

    @Override
    public R<Boolean> runBridgeHealthCheck() {
        return ruleJobHandlerApi.runBridgeHealthCheck();
    }

    @Override
    public R<Boolean> runBridgeTraceCleanup(Integer retentionDays) {
        return ruleJobHandlerApi.runBridgeTraceCleanup(retentionDays);
    }
}
