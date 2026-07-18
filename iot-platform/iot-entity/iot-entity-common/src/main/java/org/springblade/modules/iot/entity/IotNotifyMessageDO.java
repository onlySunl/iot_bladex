

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * iot通知消息 DO
 *
 * @author EnjoyIot
 */
@TableName("notify_message")
// @KeySequence("notify_message_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNotifyMessageDO extends CustomBaseEntity {

    /**
     * 内容
     */
    @AutoColumn(comment = "内容")
    @TableField("content")
    private String content;
    /**
     * 消息类型
     */
    @AutoColumn(comment = "消息类型")
    @TableField("message_type")
    private String messageType;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
