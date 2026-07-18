

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 通道模板 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_channel_template")
// @KeySequence("eiot_channel_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelTemplateDO extends CustomBaseEntity {

    /**
     * 通道模板名称
     */
    @AutoColumn(comment = "通道模板名称")
    @TableField("title")
    private String title;
    /**
     * 通道配置id
     */
    @AutoColumn(comment = "通道配置id")
    @TableField("channel_config_id")
    private Long channelConfigId;
    /**
     * 通道模板内容
     */
    @AutoColumn(comment = "通道模板内容")
    @TableField("content")
    private String content;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;


    @AutoColumn(comment = "template Code")
    @TableField("template_code")
    private String templateCode;

}
