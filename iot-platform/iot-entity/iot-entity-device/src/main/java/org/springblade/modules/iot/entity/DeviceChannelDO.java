
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

import java.time.LocalDateTime;

/**
 * 监控设备通道信息 DO
 *
 * @author EnjoyIot
 */
@TableName("sip_device_channel")
// @KeySequence("sip_device_channel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceChannelDO extends CustomBaseEntity {

    /**
     * 租户名称
     */
    @AutoColumn(comment = "租户名称")
    @TableField("tenant_name")
    private String tenantName;
    /**
     * 产品名称
     */
    @AutoColumn(comment = "产品名称")
    @TableField("product_name")
    private String productName;
    /**
     * 产品ID
     */
    @AutoColumn(comment = "产品ID")
    @TableField("user_id")
    private Long userId;
    /**
     * 产品名称
     */
    @AutoColumn(comment = "产品名称")
    @TableField("user_name")
    private String userName;
    /**
     * 设备SipID
     */
    @AutoColumn(comment = "设备SipID")
    @TableField("device_sip_id")
    private String deviceSipId;
    /**
     * 通道SipID
     */
    @AutoColumn(comment = "通道SipID")
    @TableField("channel_sip_id")
    private String channelSipId;
    /**
     * 通道名称
     */
    @AutoColumn(comment = "通道名称")
    @TableField("channel_name")
    private String channelName;
    /**
     * 注册时间
     */
    @AutoColumn(comment = "注册时间")
    @TableField("register_time")
    private LocalDateTime registerTime;
    /**
     * 设备类型
     */
    @AutoColumn(comment = "设备类型")
    @TableField("device_type")
    private String deviceType;
    /**
     * 通道类型
     */
    @AutoColumn(comment = "通道类型")
    @TableField("channel_type")
    private String channelType;
    /**
     * 城市编码
     */
    @AutoColumn(comment = "城市编码")
    @TableField("city_code")
    private String cityCode;
    /**
     * 行政区域
     */
    @AutoColumn(comment = "行政区域")
    @TableField("civil_code")
    private String civilCode;
    /**
     * 厂商名称
     */
    @AutoColumn(comment = "厂商名称")
    @TableField("manufacture")
    private String manufacture;
    /**
     * 产品型号
     */
    @AutoColumn(comment = "产品型号")
    @TableField("model")
    private String model;
    /**
     * 设备归属
     */
    @AutoColumn(comment = "设备归属")
    @TableField("owner")
    private String owner;
    /**
     * 警区
     */
    @AutoColumn(comment = "警区")
    @TableField("block")
    private String block;
    /**
     * 安装地址
     */
    @AutoColumn(comment = "安装地址")
    @TableField("address")
    private String address;
    /**
     * 父级id
     */
    @AutoColumn(comment = "父级id")
    @TableField("parent_id")
    private String parentId;
    /**
     * 设备入网IP
     */
    @AutoColumn(comment = "设备入网IP")
    @TableField("ip_address")
    private String ipAddress;
    /**
     * 设备接入端口号
     */
    @AutoColumn(comment = "设备接入端口号")
    @TableField("port")
    private Long port;
    /**
     * 密码
     */
    @AutoColumn(comment = "密码")
    @TableField("password")
    private String password;
    /**
     * PTZ类型
     */
    @AutoColumn(comment = "PTZ类型")
    @TableField("p_tz_type")
    private Long pTZType;
    /**
     * PTZ类型描述字符串
     */
    @AutoColumn(comment = "PTZ类型描述字符串")
    @TableField("p_tz_type_text")
    private String pTZTypeText;
    /**
     * 设备经度
     */
    @AutoColumn(comment = "设备经度")
    @TableField("longitude")
    private Double longitude;
    /**
     * 设备纬度
     */
    @AutoColumn(comment = "设备纬度")
    @TableField("latitude")
    private Double latitude;
    /**
     * 流媒体ID
     */
    @AutoColumn(comment = "流媒体ID")
    @TableField("stream_id")
    private String streamId;
    /**
     * 子设备数
     */
    @AutoColumn(comment = "子设备数")
    @TableField("sub_count")
    private Long subCount;
    /**
     * 是否有子设备（1-有, 0-没有）
     */
    @AutoColumn(comment = "是否有子设备（1-有, 0-没有）")
    @TableField("parental")
    private Integer parental;
    /**
     * 是否含有音频（1-有, 0-没有）
     */
    @AutoColumn(comment = "是否含有音频（1-有, 0-没有）")
    @TableField("has_audio")
    private Integer hasAudio;
    /**
     * productKey
     */
    @AutoColumn(comment = "productKey")
    @TableField("product_key")
    private String productKey;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
