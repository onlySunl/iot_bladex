package org.springblade.modules.iot.rule.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.rule.facade.RuleOpenInnerFacade;
import org.springblade.modules.iot.service.script.RuleGroovyScriptService;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptDirectCompileParam;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author mqttsnet
 * @date 2025/4/15 15:03
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleOpenInnerFacadeImpl implements RuleOpenInnerFacade {

    private final RuleGroovyScriptService ruleGroovyScriptService;

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScript(RuleGroovyScriptExecuteScriptParam param) {
        try {
            return R.success(ruleGroovyScriptService.executeScript(param));
        } catch (ServiceException bizException) {
            log.warn("Business exception while executing script: ", bizException);
            return R.fail(bizException);
        } catch (Exception e) {
            log.error("Unexpected error while executing script: ", e);
            return R.fail("Unexpected error executing script: " + e.getMessage());
        }
    }

    @Override
    public R<GroovyScriptEngineExecutorResultVO> executeScriptContent(RuleGroovyScriptDirectCompileParam param) {
        try {
            return R.success(ruleGroovyScriptService.runDirectCompile(param));
        } catch (ServiceException bizException) {
            log.warn("Business exception while executing script content: ", bizException);
            return R.fail(bizException);
        } catch (Exception e) {
            log.error("Unexpected error while executing script content: ", e);
            return R.fail("Unexpected error executing script content: " + e.getMessage());
        }
    }
}
