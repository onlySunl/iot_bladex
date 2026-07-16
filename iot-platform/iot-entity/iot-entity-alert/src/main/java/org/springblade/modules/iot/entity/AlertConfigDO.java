
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
 * 报警配置 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_alert_config")
// @KeySequence("eiot_alert_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertConfigDO extends CustomBaseEntity {

    /**
     * 告警名称
     */
    @AutoColumn(comment = "告警名称")
    @TableField("name")
    private String name;
    /**
     * 关联消息转发模板ID
     */
    @AutoColumn(comment = "关联消息转发模板ID")
    @TableField("message_template_id")
    private Long messageTemplateId;
    /**
     * 规则引擎id
     */
    @AutoColumn(comment = "规则引擎id")
    @TableField("rule_info_id")
    private Long ruleInfoId;
    /**
     * 告警等级
     */
    @AutoColumn(comment = "告警等级")
    @TableField("level")
    private String level;

}
