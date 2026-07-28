package org.springblade.modules.iot.entity.alarm;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import org.springblade.basic.base.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * <p>
 * 实体类
 * 告警记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_alarm_record", comment = "RuleAlarmRecord table")
public class RuleAlarmRecord extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 告警编码
     */
    
    @AutoColumn(value = "alarm_identification", comment = "告警编码")
    private String alarmIdentification;
    /**
     * 发生时间
     */
    
    @AutoColumn(value = "occurred_time", comment = "发生时间")
    private LocalDateTime occurredTime;
    /**
     * 处理时间
     */
    
    @AutoColumn(value = "handled_time", comment = "处理时间")
    private LocalDateTime handledTime;
    /**
     * 处理记录
     */
    
    @AutoColumn(value = "handling_notes", comment = "处理记录")
    private String handlingNotes;
    /**
     * 解决时间
     */
    
    @AutoColumn(value = "resolved_time", comment = "解决时间")
    private LocalDateTime resolvedTime;
    /**
     * 解决记录
     */
    
    @AutoColumn(value = "resolution_notes", comment = "解决记录")
    private String resolutionNotes;
    /**
     * 告警具体内容信息
     */
    
    @AutoColumn(value = "content_data", comment = "告警具体内容信息")
    private String contentData;
    /**
     * 处理状态
     */
    
    @AutoColumn(value = "handled_status", comment = "处理状态")
    private Integer handledStatus;
    }
