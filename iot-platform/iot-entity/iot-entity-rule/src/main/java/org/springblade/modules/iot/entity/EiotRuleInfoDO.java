
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
    @AutoColumn
    @TableField("name")
    private String name;
    /**
     * 监听器
     */
    @AutoColumn
    @TableField("listeners")
    private String listeners;
    /**
     * 过滤器
     */
    @AutoColumn
    @TableField("filters")
    private String filters;
    /**
     * 动作
     */
    @AutoColumn
    @TableField("actions")
    private String actions;
    /**
     * 触发控制配置(JSON)：频率限制/延时/告警解除
     */
    @AutoColumn
    @TableField("trigger_options")
    private String triggerOptions;
    /**
     * 类型(1数据流转 2场景联动)
     */
    @AutoColumn
    @TableField("typ")
    private String typ;
    /**
     * 状态(0启用 1禁用)
     */
    @AutoColumn
    @TableField("state")
    private Integer state;
    /**
     * 机构id
     */
    @AutoColumn
    @TableField("dept_id")
    private Long deptId;

}
