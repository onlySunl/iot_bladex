package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;


import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@TableName("qs_device_alarm")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "qs_device_alarm", comment = "设备告警表")
public class QsDeviceAlarm extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;


    /** 设备ID */
    @TableField(value = "device_id")
    @AutoColumn(comment = "设备ID", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long deviceId;

    /** 设备编号 */
    @TableField(value = "device_code")
    @AutoColumn(comment = "设备编号", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceCode;

    /** 设备名称 */
    @TableField(value = "device_name")
    @AutoColumn(comment = "设备名称", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceName;

    /** 告警类型 */
    @TableField(value = "alarm_type")
    @AutoColumn(comment = "告警类型", length = 32, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String alarmType;

    /** 告警级别 */
    @TableField(value = "alarm_level")
    @AutoColumn(comment = "告警级别", length = 32, defaultValueType=DefaultValueEnum.EMPTY_STRING)
    private String alarmLevel;

    /** SDK类型 */
    @TableField(value = "sdk_type")
    @AutoColumn(comment = "SDK类型", length = 32, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String sdkType;

    /** 通道号 */
    @TableField(value = "channel")
    @AutoColumn(comment = "通道号", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer channel;

    /** 告警时间 */
    @TableField(value = "alarm_time")
    @AutoColumn(comment = "告警时间", defaultValueType = DefaultValueEnum.NULL)
    private Date alarmTime;

    /** 告警内容 */
    @TableField(value = "alarm_content")
    @AutoColumn(comment = "告警内容", length = 65535, defaultValueType = DefaultValueEnum.NULL)
    private String alarmContent;

    /** 告警图片URL */
    @TableField(value = "image_url")
    @AutoColumn(comment = "告警图片URL", length = 512, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String imageUrl;

    /** 是否已处理（0-否，1-是） */
    @TableField(value = "handled")
    @AutoColumn(comment = "是否已处理", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer handled;

    /** 处理人 */
    @TableField(value = "handler")
    @AutoColumn(comment = "处理人", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String handler;

    /** 处理时间 */
    @TableField(value = "handle_time")
    @AutoColumn(comment = "处理时间", defaultValueType = DefaultValueEnum.NULL)
    private Date handleTime;



    /**
     * 批量处理用的id数组（非数据库字段）
     */
    @TableField(exist = false)
    private Long[] ids;
}
