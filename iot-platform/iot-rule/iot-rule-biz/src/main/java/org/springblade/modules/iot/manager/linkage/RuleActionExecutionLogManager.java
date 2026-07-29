package org.springblade.modules.iot.manager.linkage;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.entity.linkage.RuleActionExecutionLog;
import org.springblade.modules.iot.vo.query.linkage.RuleActionExecutionLogPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 规则动作执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:54:41
 * @create [2024-12-02 18:54:41] [mqttsnet]
 */
public interface RuleActionExecutionLogManager extends SuperManager<RuleActionExecutionLog> {


    /**
     * 获取规则动作执行日志列表
     *
     * @param query 查询条件 {@link RuleActionExecutionLogPageQuery}
     * @return 规则动作执行日志列表 {@link RuleActionExecutionLog}
     */
    List<RuleActionExecutionLog> getRuleActionExecutionLogList(RuleActionExecutionLogPageQuery query);
}


