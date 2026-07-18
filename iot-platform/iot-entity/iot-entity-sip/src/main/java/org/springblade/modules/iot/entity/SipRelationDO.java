

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 监控设备关联 DO
 *
 * @author EnjoyIot
 */
@TableName("iot_sip_relation")
// @KeySequence("iot_sip_relation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SipRelationDO extends CustomBaseEntity {

    /**
     * 监控设备编号
     */
    @AutoColumn(comment = "监控设备编号")
    @TableField("channel_id")
    private String channelId;
    /**
     * 关联的设备id
     */
    @AutoColumn(comment = "关联的设备id")
    @TableField("re_device_id")
    private Long reDeviceId;
    /**
     * 关联的场景id
     */
    @AutoColumn(comment = "关联的场景id")
    @TableField("re_scene_model_id")
    private Long reSceneModelId;

}
