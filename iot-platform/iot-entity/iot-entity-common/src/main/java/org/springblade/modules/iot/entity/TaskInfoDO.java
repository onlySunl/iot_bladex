

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * iot任务 DO
 *
 * @author EnjoyIotEnjoyIot
 */
@TableName("iot_task_info")
// @KeySequence("task_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskInfoDO extends CustomBaseEntity {

    /**
     * 任务名称
     */
    @AutoColumn(comment = "任务名称")
    @TableField("name")
    private String name;
    /**
     * 任务输出
     */
    @AutoColumn(comment = "任务输出")
    @TableField("actions")
    private String actions;
    /**
     * 状态
     */
    @AutoColumn(comment = "状态")
    @TableField("state")
    private String state;
    /**
     * 任务类型
     */
    @AutoColumn(comment = "任务类型")
    @TableField("type")
    private String type;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

    @AutoColumn(comment = "expression")
    @TableField("expression")
    private String expression;


}
