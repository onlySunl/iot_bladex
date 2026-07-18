

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

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
