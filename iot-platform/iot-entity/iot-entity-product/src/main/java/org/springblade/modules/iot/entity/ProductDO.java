
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
 * 物联网产品 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_product")
// @KeySequence("eiot_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO extends CustomBaseEntity {

    /**
     * 产品名称
     */
    @AutoColumn
    @TableField("name")
    private String name;
    /**
     * 产品分类id
     */
    @AutoColumn
    @TableField("category_id")
    private Long categoryId;
    /**
     * productKey
     */
    @AutoColumn
    @TableField("product_key")
    private String productKey;
    /**
     * mcu code
     */
    @AutoColumn
    @TableField("mcu_code")
    private String mcuCode;
    /**
     * 功能介绍
     */
    @AutoColumn
    @TableField("remark1")
    private String remark1;
    /**
     * 图片url
     */
    @AutoColumn
    @TableField("img_url")
    private String imgUrl;
    /**
     * 设备类型(0 网关设备, 1 网关子设备, 2 直连设备, 3 非联网设备 )
     */
    @AutoColumn
    @TableField("node_type")
    private Integer nodeType;
    /**
     * 协议code
     */
    @AutoColumn
    @TableField("protocol_code")
    private String protocolCode;
    /**
     * 保活时间
     */
    @AutoColumn
    @TableField("keep_alive_time")
    private Long keepAliveTime;
    /**
     * 产品密钥
     */
    @AutoColumn
    @TableField("product_secret")
    private String productSecret;
    /**
     * 是否透传
     */
    @AutoColumn
    @TableField("transparent")
    private Boolean transparent;

    @AutoColumn
    @TableField("locate_type")
    private Integer locateType;
}
