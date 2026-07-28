package org.springblade.modules.iot.manager.alarm.impl;

import cn.hutool.core.collection.CollUtil;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.entity.alarm.RuleAlarmChannel;
import org.springblade.modules.iot.manager.alarm.RuleAlarmChannelManager;
import org.springblade.modules.iot.mapper.alarm.RuleAlarmChannelMapper;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmChannelPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 * @create [2023-09-09 21:14:58] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleAlarmChannelManagerImpl extends SuperManagerImpl<RuleAlarmChannelMapper, RuleAlarmChannel> implements RuleAlarmChannelManager {

    private final RuleAlarmChannelMapper ruleAlarmChannelMapper;

    /**
     * Retrieve a list of RuleAlarmChannel based on the provided query.
     *
     * @param query Search parameters for filtering RuleAlarmChannel.
     * @return List of RuleAlarmChannel that match the provided criteria.
     */
    @Override
    public List<RuleAlarmChannel> getRuleAlarmChannelList(RuleAlarmChannelPageQuery query) {
        QueryWrap<RuleAlarmChannel> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != query.getChannelType(), RuleAlarmChannel::getChannelType, query.getChannelType());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getIds()), RuleAlarmChannel::getId, query.getIds());
        return ruleAlarmChannelMapper.selectList(queryWrap);
    }
}


