
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.entity;

import com.tangzc.mybatisflex.autotable.annotations.AutoColumn;

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
@TableName("eiot_component")
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