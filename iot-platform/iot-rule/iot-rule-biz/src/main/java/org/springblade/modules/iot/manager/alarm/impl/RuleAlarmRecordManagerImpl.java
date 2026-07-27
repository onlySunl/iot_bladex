package org.springblade.modules.iot.manager.alarm.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.alarm.RuleAlarmRecord;
import org.springblade.modules.iot.manager.alarm.RuleAlarmRecordManager;
import org.springblade.modules.iot.mapper.alarm.RuleAlarmRecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 告警记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:15:22
 * @create [2023-09-09 21:15:22] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleAlarmRecordManagerImpl extends BaseServiceImpl<RuleAlarmRecordMapper, RuleAlarmRecord> implements RuleAlarmRecordManager {

}


