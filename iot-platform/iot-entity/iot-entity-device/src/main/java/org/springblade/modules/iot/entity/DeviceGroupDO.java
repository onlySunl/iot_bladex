

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 设备分组 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_iot_device_group")
// @KeySequence("eiot_iot_device_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceGroupDO extends CustomBaseEntity {

    /**
     * 设备ID
     */
    @AutoColumn(comment = "设备ID")
    @TableField("device_id")
    private Long deviceId;
    /**
     * 分组ID
     */
    @AutoColumn(comment = "分组ID")
    @TableField("group_id")
    private Long groupId;

}
