

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组件配置 DO
 */
@TableName("iot_component")
// @KeySequence("eiot_component_seq") 
@Data
@EqualsAndHashCode(callSuper = true)
public class ComponentDO extends CustomBaseEntity {


    /**
     * 组件名称
     */
    @AutoColumn(comment = "组件名称")
    @TableField("name")
    private String name;

    /**
     * 组件类型
     */
    @AutoColumn(comment = "组件类型")
    @TableField("type")
    private String type;

    /**
     * 组件配置(JSON格式)
     */
    @AutoColumn(comment = "组件配置(JSON格式)")
    @TableField("config")
    private String config;



}