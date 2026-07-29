package org.springblade.modules.iot.entity.alarm;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;


/**
 * <p>
 * 实体类
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_alarm_channel", comment = "RuleAlarmChannel table")
public class RuleAlarmChannel extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 渠道名称
     */
    
    @AutoColumn(value = "channel_name", comment = "渠道名称")
    private String channelName;
    /**
     * 渠道类型
     */
    
    @AutoColumn(value = "channel_type", comment = "渠道类型")
    private Integer channelType;
    /**
     * 告警配置
     */
    
    @AutoColumn(value = "channel_config", comment = "告警配置")
    private String channelConfig;
    }
