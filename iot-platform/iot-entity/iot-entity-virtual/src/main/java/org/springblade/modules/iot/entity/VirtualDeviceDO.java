

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

/**
 * 虚拟设备DO
 *
 * @author clickear
 */
@TableName("eiot_virtual_device")
// @KeySequence("eiot_virtual_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualDeviceDO extends CustomBaseEntity {


    /**
     * 虚拟设备名称
     */
    @AutoColumn(comment = "虚拟设备名称")
    @TableField("name")
    private String name;

    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;

    /**
     * 虚拟类型
     */
    @AutoColumn(comment = "虚拟类型")
    @TableField("type")
    private String type;

    /**
     * 设备行为脚本
     */
    @AutoColumn(comment = "设备行为脚本")
    @TableField("script")
    private String script;

    /**
     * 触发方式执行方式
     */
    @TableField("`trigger`") //TODO 启用 PostgreSQL、KaiwuDB 需要注释掉这个注解
    @AutoColumn(comment = "触发方式执行方式")
    private String trigger;

    /**
     * 触发表达式
     */
    @AutoColumn(comment = "触发表达式")
    @TableField("trigger_expression")
    private String triggerExpression;

    /**
     * 运行状态
     */
    @AutoColumn(comment = "运行状态")
    @TableField("state")
    private String state;

}
