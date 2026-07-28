package org.springblade.modules.iot.rule.api.hystrix;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.rule.api.RuleJobHandlerApi;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.linkage.RuleDetailsResultVO;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import org.springframework.stereotype.Component;

/**
 * @program: thinglinks-cloud
 * @description: RuleJobHandlerApi API熔断
 * @packagename: org.springblade.modules.iot.rule.api.hystrix
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2024-07-22 12:37
 **/
@Component
public class RuleJobHandlerApiFallback implements RuleJobHandlerApi {

    @Override
    public R<RuleDetailsResultVO> triggerRulePolicy(Long tenantId, String ruleIdentification) {
        return R.timeout();
    }

    @Override
    public R<Boolean> flushGroovyScriptCache() {
        return R.timeout();
    }

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScript(RuleGroovyScriptExecuteScriptParam param) {
        return R.timeout();
    }

    @Override
    public R<Boolean> runBridgeHealthCheck() {
        return R.timeout();
    }

    @Override
    public R<Boolean> runBridgeTraceCleanup(Integer retentionDays) {
        return R.timeout();
    }
}
