package org.springblade.modules.iot.mapper.alarm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.alarm.RuleAlarm;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface RuleAlarmMapper extends BladeMapper<RuleAlarm> {

}


