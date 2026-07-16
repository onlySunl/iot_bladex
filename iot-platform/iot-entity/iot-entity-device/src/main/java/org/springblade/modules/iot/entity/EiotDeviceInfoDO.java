
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
 * 设备信息 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_device_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EiotDeviceInfoDO extends CustomBaseEntity {

    /**
     * 设备唯一标识
     */
    @AutoColumn(comment = "设备唯一标识")
    @TableField("dn")
    private String dn;
    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;
    /**
     * 设备属性
     */
    @AutoColumn(comment = "设备属性")
    @TableField("properties")
    private String properties;
    /**
     * 别名
     */
    @AutoColumn(comment = "别名")
    @TableField("name")
    private String name;
    /**
     *(0:否, 1:在线, 3-未激活，4-禁用)设备状态
     */
    @AutoColumn(comment = "(0:否, 1:在线, 3-未激活，4-禁用)设备状态")
    @TableField("state")
    private Integer state;
    /**
     * 离线时间
     */
    @AutoColumn(comment = "离线时间")
    @TableField("offline_time")
    private Long offlineTime;
    /**
     * 在线时间
     */
    @AutoColumn(comment = "在线时间")
    @TableField("online_time")
    private Long onlineTime;
    /**
     * 设备序列号
     */
    @AutoColumn(comment = "设备序列号")
    @TableField("serial_no")
    private String serialNo;
    /**
     * 经纬度
     */
    @AutoColumn(comment = "经纬度")
    @TableField("lat")
    private Double lat;
    /**
     * 经纬度
     */
    @AutoColumn(comment = "经纬度")
    @TableField("lon")
    private Double lon;
    
    @AutoColumn(comment = "model")
    @TableField("model")
    private String model;

    @AutoColumn(comment = "parent Id")
    @TableField("parent_id")
    private Long parentId;

    @AutoColumn(comment = "secret")
    @TableField("secret")
    private String secret;


    @AutoColumn(comment = "addr")
    @TableField("addr")
    private String addr;

    @AutoColumn(comment = "firm Version")
    @TableField("firm_version")
    private String firmVersion;

    @AutoColumn(comment = "node Type")
    @TableField("node_type")
    private Integer nodeType;

    @AutoColumn(comment = "transparent")
    @TableField("transparent")
    private Boolean transparent;
}
