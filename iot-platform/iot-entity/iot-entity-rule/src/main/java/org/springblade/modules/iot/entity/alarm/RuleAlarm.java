package org.springblade.modules.iot.entity.alarm;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;


/**
 * <p>
 * 实体类
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_alarm", comment = "RuleAlarm table")
public class RuleAlarm extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 告警名称
     */
    
    @AutoColumn(value = "alarm_name", comment = "告警名称")
    private String alarmName;
    /**
     * 告警编码
     */
    
    @AutoColumn(value = "alarm_identification", comment = "告警编码")
    private String alarmIdentification;
    /**
     * 告警场景
     */
    
    @AutoColumn(value = "alarm_scene", comment = "告警场景")
    private String alarmScene;
    /**
     * 告警渠道ID集合
     */
    
    @AutoColumn(value = "alarm_channel_ids", comment = "告警渠道ID集合")
    private String alarmChannelIds;
    /**
     * 告警级别
     */
    
    @AutoColumn(value = "level", comment = "告警级别")
    private Integer level;
    }
