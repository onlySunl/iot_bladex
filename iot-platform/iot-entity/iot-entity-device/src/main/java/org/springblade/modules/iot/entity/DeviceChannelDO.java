
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
    @AutoColumn
    @TableField("tenant_name")
    private String tenantName;
    /**
     * 产品名称
     */
    @AutoColumn
    @TableField("product_name")
    private String productName;
    /**
     * 产品ID
     */
    @AutoColumn
    @TableField("user_id")
    private Long userId;
    /**
     * 产品名称
     */
    @AutoColumn
    @TableField("user_name")
    private String userName;
    /**
     * 设备SipID
     */
    @AutoColumn
    @TableField("device_sip_id")
    private String deviceSipId;
    /**
     * 通道SipID
     */
    @AutoColumn
    @TableField("channel_sip_id")
    private String channelSipId;
    /**
     * 通道名称
     */
    @AutoColumn
    @TableField("channel_name")
    private String channelName;
    /**
     * 注册时间
     */
    @AutoColumn
    @TableField("register_time")
    private LocalDateTime registerTime;
    /**
     * 设备类型
     */
    @AutoColumn
    @TableField("device_type")
    private String deviceType;
    /**
     * 通道类型
     */
    @AutoColumn
    @TableField("channel_type")
    private String channelType;
    /**
     * 城市编码
     */
    @AutoColumn
    @TableField("city_code")
    private String cityCode;
    /**
     * 行政区域
     */
    @AutoColumn
    @TableField("civil_code")
    private String civilCode;
    /**
     * 厂商名称
     */
    @AutoColumn
    @TableField("manufacture")
    private String manufacture;
    /**
     * 产品型号
     */
    @AutoColumn
    @TableField("model")
    private String model;
    /**
     * 设备归属
     */
    @AutoColumn
    @TableField("owner")
    private String owner;
    /**
     * 警区
     */
    @AutoColumn
    @TableField("block")
    private String block;
    /**
     * 安装地址
     */
    @AutoColumn
    @TableField("address")
    private String address;
    /**
     * 父级id
     */
    @AutoColumn
    @TableField("parent_id")
    private String parentId;
    /**
     * 设备入网IP
     */
    @AutoColumn
    @TableField("ip_address")
    private String ipAddress;
    /**
     * 设备接入端口号
     */
    @AutoColumn
    @TableField("port")
    private Long port;
    /**
     * 密码
     */
    @AutoColumn
    @TableField("password")
    private String password;
    /**
     * PTZ类型
     */
    @AutoColumn
    @TableField("p_tz_type")
    private Long pTZType;
    /**
     * PTZ类型描述字符串
     */
    @AutoColumn
    @TableField("p_tz_type_text")
    private String pTZTypeText;
    /**
     * 设备经度
     */
    @AutoColumn
    @TableField("longitude")
    private Double longitude;
    /**
     * 设备纬度
     */
    @AutoColumn
    @TableField("latitude")
    private Double latitude;
    /**
     * 流媒体ID
     */
    @AutoColumn
    @TableField("stream_id")
    private String streamId;
    /**
     * 子设备数
     */
    @AutoColumn
    @TableField("sub_count")
    private Long subCount;
    /**
     * 是否有子设备（1-有, 0-没有）
     */
    @AutoColumn
    @TableField("parental")
    private Integer parental;
    /**
     * 是否含有音频（1-有, 0-没有）
     */
    @AutoColumn
    @TableField("has_audio")
    private Integer hasAudio;
    /**
     * productKey
     */
    @AutoColumn
    @TableField("product_key")
    private String productKey;
    /**
     * 机构id
     */
    @AutoColumn
    @TableField("dept_id")
    private Long deptId;

}
