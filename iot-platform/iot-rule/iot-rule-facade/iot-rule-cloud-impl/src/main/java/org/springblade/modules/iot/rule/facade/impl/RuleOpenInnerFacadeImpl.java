package org.springblade.modules.iot.rule.facade.impl;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.rule.api.RuleOpenInnerApi;
import org.springblade.modules.iot.rule.facade.RuleOpenInnerFacade;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptDirectCompileParam;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author mqttsnet
 * @date 2025/4/15 15:03
 */
@Slf4j
@Service("RuleOpenInnerFacade")
public class RuleOpenInnerFacadeImpl implements RuleOpenInnerFacade {

    @Autowired
    @Lazy
    private RuleOpenInnerApi ruleOpenInnerApi;

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScript(RuleGroovyScriptExecuteScriptParam param) {
        return ruleOpenInnerApi.executeScript(param);
    }

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScriptContent(RuleGroovyScriptDirectCompileParam param) {
        return ruleOpenInnerApi.executeScriptContent(param);
    }
}
