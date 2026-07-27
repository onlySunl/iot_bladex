package org.springblade.modules.iot.mapper.alarm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.alarm.RuleAlarmChannel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 * @create [2023-09-09 21:14:58] [mqttsnet]
 */
@Mapper
public interface RuleAlarmChannelMapper extends BladeMapper<RuleAlarmChannel> {

}


