package org.springblade.modules.iot.manager.alarm;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.alarm.RuleAlarm;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 * @create [2023-09-09 21:14:37] [mqttsnet]
 */
public interface RuleAlarmManager extends BaseService<RuleAlarm> {

    /**
     * Retrieve a list of RuleAlarm based on the provided query.
     *
     * @param query Search parameters for filtering RuleAlarm.
     * @return List of RuleAlarm that match the provided criteria.
     */
    List<RuleAlarm> getRuleAlarmList(RuleAlarmPageQuery query);
}


