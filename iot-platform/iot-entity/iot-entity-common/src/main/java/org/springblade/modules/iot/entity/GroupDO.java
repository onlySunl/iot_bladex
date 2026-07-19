

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
@TableName("iot_iot_group")
// @KeySequence("eiot_iot_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends CustomBaseEntity {

    /**
     * 分组名称
     */
    @AutoColumn(comment = "分组名称")
    @TableField("name")
    private String name;
    /**
     * 分组排序
     */
    @AutoColumn(comment = "分组排序")
    @TableField("group_order")
    private Integer groupOrder;
    /**
     * 用户ID
     */
    @AutoColumn(comment = "用户ID")
    @TableField("uid")
    private Long uid;
    /**
     * 用户昵称
     */
    @AutoColumn(comment = "用户昵称")
    @TableField("user_name")
    private String userName;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;
    /**
     * 分组类型(0系统, 1用户, 字典)
     */
    @AutoColumn(comment = "分组类型(0系统, 1用户, 字典)")
    @TableField("typ")
    private Integer typ;

}
