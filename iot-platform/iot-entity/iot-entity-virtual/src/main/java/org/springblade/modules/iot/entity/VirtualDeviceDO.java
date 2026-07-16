
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
