
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
 * 监控设备 DO
 *
 * @author EnjoyIot
 */
@TableName("sip_device")
// @KeySequence("sip_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SipDeviceDO extends CustomBaseEntity {

    /**
     * 设备ID
     */
    @TableId
    @AutoColumn(comment = "设备ID")
    @TableField("device_id")
    private Long deviceId;
    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 产品名称
     */
    @AutoColumn(comment = "产品名称")
    @TableField("product_name")
    private String productName;
    /**
     * 设备SipID
     */
    @AutoColumn(comment = "设备SipID")
    @TableField("device_sip_id")
    private String deviceSipId;
    /**
     * 设备名称
     */
    @AutoColumn(comment = "设备名称")
    @TableField("device_name")
    private String deviceName;
    /**
     * 厂商名称
     */
    @AutoColumn(comment = "厂商名称")
    @TableField("manufacturer")
    private String manufacturer;
    /**
     * 产品型号
     */
    @AutoColumn(comment = "产品型号")
    @TableField("model")
    private String model;
    /**
     * 固件版本
     */
    @AutoColumn(comment = "固件版本")
    @TableField("firmware")
    private String firmware;
    /**
     * 传输模式
     */
    @AutoColumn(comment = "传输模式")
    @TableField("transport")
    private String transport;
    /**
     * 流模式
     */
    @AutoColumn(comment = "流模式")
    @TableField("stream_mode")
    private String streamMode;
    /**
     * 在线状态
     */
    @AutoColumn(comment = "在线状态")
    @TableField("online")
    private String online;
    /**
     * 注册时间
     */
    @AutoColumn(comment = "注册时间")
    @TableField("register_time")
    private LocalDateTime registerTime;
    /**
     * 最后上线时间
     */
    @AutoColumn(comment = "最后上线时间")
    @TableField("last_connect_time")
    private LocalDateTime lastConnectTime;
    /**
     * 激活时间
     */
    @AutoColumn(comment = "激活时间")
    @TableField("active_time")
    private LocalDateTime activeTime;
    /**
     * 设备入网IP
     */
    @AutoColumn(comment = "设备入网IP")
    @TableField("ip")
    private String ip;
    /**
     * 设备接入端口号
     */
    @AutoColumn(comment = "设备接入端口号")
    @TableField("port")
    private Long port;
    /**
     * 设备地址
     */
    @AutoColumn(comment = "设备地址")
    @TableField("host_address")
    private String hostAddress;

}
