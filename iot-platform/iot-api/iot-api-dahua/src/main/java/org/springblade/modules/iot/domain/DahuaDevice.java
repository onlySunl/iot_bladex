package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 大华设备对象 dahua_device
 *
 * @author fengcheng
 * @date 2025-06-06
 */
@Data
@TableName("dahua_device")
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "dahua_device", comment = "大华设备表")
public class DahuaDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableField(value = "id")
    @AutoColumn(comment = "主键ID", length = 20)
    private Long id;

    /** IP */
    @TableField(value = "ip")
    @AutoColumn(comment = "IP地址", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String ip;

    /** 设备ID */
    @TableField(value = "device_id")
    @AutoColumn(comment = "设备ID", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceId;

    /** 端口 */
    @TableField(value = "port")
    @AutoColumn(comment = "端口", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String port;

    /** 用户名 */
    @TableField(value = "username")
    @AutoColumn(comment = "用户名", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String username;

    /** 密码 */
    @TableField(value = "password")
    @AutoColumn(comment = "密码", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String password;
}
