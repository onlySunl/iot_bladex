package org.springblade.modules.iot.manager.alarm;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.alarm.RuleAlarmChannel;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmChannelPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 * @create [2023-09-09 21:14:58] [mqttsnet]
 */
public interface RuleAlarmChannelManager extends BaseService<RuleAlarmChannel> {

    /**
     * Retrieve a list of RuleAlarmChannel based on the provided query.
     *
     * @param query Search parameters for filtering RuleAlarmChannel.
     * @return List of RuleAlarmChannel that match the provided criteria.
     */
    List<RuleAlarmChannel> getRuleAlarmChannelList(RuleAlarmChannelPageQuery query);

}


