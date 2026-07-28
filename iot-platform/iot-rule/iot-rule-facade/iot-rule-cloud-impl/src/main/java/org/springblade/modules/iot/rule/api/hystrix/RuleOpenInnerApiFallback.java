package org.springblade.modules.iot.rule.api.hystrix;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.rule.api.RuleOpenInnerApi;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptDirectCompileParam;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import org.springframework.stereotype.Component;

/**
 * ============================================================================
 * Description:
 * <p>
 * Rule AnyUser API熔断
 * ============================================================================
 *
 * @author Sun Shihuan
 * @version 1.0.0
 * @email
 * @date 2025/4/15 15:01
 */
@Component
public class RuleOpenInnerApiFallback implements RuleOpenInnerApi {

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScript(RuleGroovyScriptExecuteScriptParam param) {
        return R.timeout();
    }

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScriptContent(RuleGroovyScriptDirectCompileParam param) {
        return R.timeout();
    }
}
