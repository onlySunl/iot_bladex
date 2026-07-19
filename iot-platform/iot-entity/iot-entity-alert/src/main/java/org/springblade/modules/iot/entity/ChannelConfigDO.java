package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 通道配置 DO
 *
 * @author EnjoyIot
 */
@TableName("iot_channel_config")
// @KeySequence("eiot_channel_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelConfigDO extends CustomBaseEntity {

    /**
     * 配置名称
     */
    @AutoColumn(comment = "配置名称")
    @TableField("title")
    private String title;
    /**
     * 通道编码
     */
    @AutoColumn(comment = "通道编码")
    @TableField("code")
    private String code;
    /**
     * 通道配置参数
     */
    @AutoColumn(comment = "通道配置参数")
    @TableField("param")
    private String param;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
