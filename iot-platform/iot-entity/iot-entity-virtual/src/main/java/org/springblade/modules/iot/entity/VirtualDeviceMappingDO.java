

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 虚拟设备-映射
 *
 * @author clickear
 */
@TableName("iot_virtual_device_mapping")
// @KeySequence("eiot_virtual_device_mapping_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualDeviceMappingDO extends CustomBaseEntity {


    /**
     * 虚拟设备id
     */
    @AutoColumn(comment = "虚拟设备id")
    @TableField("virtual_device_id")
    private Long virtualDeviceId;

    /**
     * 设备id
     */
    @AutoColumn(comment = "设备id")
    @TableField("device_id")
    private Long deviceId;

}
