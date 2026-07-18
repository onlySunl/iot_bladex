

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 规则引擎 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_rule_info")
// @KeySequence("eiot_rule_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EiotRuleInfoDO extends CustomBaseEntity {

    /**
     * 规则名称
     */
    @AutoColumn(comment = "规则名称")
    @TableField("name")
    private String name;
    /**
     * 监听器
     */
    @AutoColumn(comment = "监听器")
    @TableField("listeners")
    private String listeners;
    /**
     * 过滤器
     */
    @AutoColumn(comment = "过滤器")
    @TableField("filters")
    private String filters;
    /**
     * 动作
     */
    @AutoColumn(comment = "动作")
    @TableField("actions")
    private String actions;
    /**
     * 触发控制配置(JSON)：频率限制/延时/告警解除
     */
    @AutoColumn(comment = "触发控制配置(JSON)：频率限制/延时/告警解除")
    @TableField("trigger_options")
    private String triggerOptions;
    /**
     * 类型(1数据流转 2场景联动)
     */
    @AutoColumn(comment = "类型(1数据流转 2场景联动)")
    @TableField("typ")
    private String typ;
    /**
     * 状态(0启用 1禁用)
     */
    @AutoColumn(comment = "状态(0启用 1禁用)")
    @TableField("state")
    private Integer state;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
