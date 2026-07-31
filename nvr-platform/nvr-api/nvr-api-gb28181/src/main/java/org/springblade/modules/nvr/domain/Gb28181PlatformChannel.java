package org.springblade.modules.nvr.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.basic.base.entity.Entity;

/**
 * 国标GB28181平台通道关联对象 qs_gb28181_platform_channel
 *
 * @author ruoyi
 */
@Data
@TableName("qs_gb28181_platform_channel")
@EqualsAndHashCode(callSuper = true)
@AutoTable(value = "qs_gb28181_platform_channel", comment = "国标28181平台通道表")
public class Gb28181PlatformChannel extends Entity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（继承自 CustomBaseEntity） */

    /** 国标GB28181平台ID */
    @TableField(value = "platform_id")
    @AutoColumn(comment = "国标28181级联id", length = 20)
    private Long platformId;

    /** 设备ID */
    @TableField(value = "device_id")
    @AutoColumn(comment = "设备id", length = 20)
    private Long deviceId;
}
