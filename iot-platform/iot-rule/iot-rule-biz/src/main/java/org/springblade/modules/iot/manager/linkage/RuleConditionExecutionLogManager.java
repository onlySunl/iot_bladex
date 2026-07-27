package org.springblade.modules.iot.manager.linkage;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.linkage.RuleConditionExecutionLog;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionExecutionLogPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 规则条件执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:53:47
 * @create [2024-12-02 18:53:47] [mqttsnet]
 */
public interface RuleConditionExecutionLogManager extends BaseService<RuleConditionExecutionLog> {


    /**
     * 获取规则条件执行日志列表
     *
     * @param query 查询条件 {@link RuleConditionExecutionLogPageQuery}
     * @return 规则条件执行日志列表 {@link RuleConditionExecutionLog}
     */
    List<RuleConditionExecutionLog> getRuleConditionExecutionLogList(RuleConditionExecutionLogPageQuery query);
}


