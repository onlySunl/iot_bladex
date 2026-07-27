package org.springblade.modules.iot.manager.script;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.script.RuleGroovyScript;
import org.springblade.modules.iot.vo.query.script.RuleGroovyScriptPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 规则脚本表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-03-24 09:54:10
 * @create [2025-03-24 09:54:10] [mqttsnet]
 */
public interface RuleGroovyScriptManager extends BaseService<RuleGroovyScript> {


    /**
     * 查询规则脚本列表
     *
     * @param query 查询参数
     * @return {@link List<RuleGroovyScript>} 规则脚本列表
     */
    List<RuleGroovyScript> getRuleGroovyScriptList(RuleGroovyScriptPageQuery query);
}


