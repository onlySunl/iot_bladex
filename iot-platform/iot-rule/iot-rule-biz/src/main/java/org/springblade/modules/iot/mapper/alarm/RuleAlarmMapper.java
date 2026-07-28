package org.springblade.modules.iot.mapper.alarm;

import org.springblade.core.database.mybatis.BladeMapper;
import org.springblade.modules.iot.entity.alarm.RuleAlarm;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 * @create [2023-09-09 21:14:37] [mqttsnet]
 */
@Repository
public interface RuleAlarmMapper extends BladeMapper<RuleAlarm> {

}


