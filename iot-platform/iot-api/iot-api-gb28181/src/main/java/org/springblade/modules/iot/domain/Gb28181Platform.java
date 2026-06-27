package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国标GB28181平台配置对象 gb28181_platform
 *
 * @author ruoyi
 */
@Data
@TableName("gb28181_platform")
@EqualsAndHashCode(callSuper = true)
@Table(value = "gb28181_platform", comment = "国标GB28181平台配置表")
public class Gb28181Platform extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableField(value = "id")
    @AutoColumn(comment = "主键ID", length = 20)
    private Long id;

    /** 是否启用：0-禁用，1-启用 */
    @TableField(value = "enable")
    @AutoColumn(comment = "是否启用", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer enable;

    /** 平台名称 */
    @TableField(value = "name")
    @AutoColumn(comment = "平台名称", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String name;

    /** 平台国标编码（SIP服务器ID） */
    @TableField(value = "server_gb_id")
    @AutoColumn(comment = "平台国标编码", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverGbId;

    /** 平台域（SIP域） */
    @TableField(value = "server_gb_domain")
    @AutoColumn(comment = "平台域", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverGbDomain;

    /** 平台服务器IP地址 */
    @TableField(value = "server_ip")
    @AutoColumn(comment = "平台服务器IP地址", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverIp;

    /** 平台服务器端口 */
    @TableField(value = "server_port")
    @AutoColumn(comment = "平台服务器端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer serverPort;

    /** 设备国标编码（本地SIP设备ID） */
    @TableField(value = "device_gb_id")
    @AutoColumn(comment = "设备国标编码", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceGbId;

    /** 设备IP地址 */
    @TableField(value = "device_ip")
    @AutoColumn(comment = "设备IP地址", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceIp;

    /** 设备端口 */
    @TableField(value = "device_port")
    @AutoColumn(comment = "设备端口", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String devicePort;

    /** SIP认证用户名 */
    @TableField(value = "username")
    @AutoColumn(comment = "SIP认证用户名", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String username;

    /** SIP认证密码 */
    @TableField(value = "password")
    @AutoColumn(comment = "SIP认证密码", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String password;

    /** 注册有效期（秒） */
    @TableField(value = "expires")
    @AutoColumn(comment = "注册有效期", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String expires;

    /** 心跳超时时间（秒） */
    @TableField(value = "keep_timeout")
    @AutoColumn(comment = "心跳超时时间", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String keepTimeout;

    /** 传输协议：UDP/TCP */
    @TableField(value = "transport")
    @AutoColumn(comment = "传输协议", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String transport;

    /** 行政区划编码 */
    @TableField(value = "civil_code")
    @AutoColumn(comment = "行政区划编码", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String civilCode;

    /** 设备厂商 */
    @TableField(value = "manufacturer")
    @AutoColumn(comment = "设备厂商", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String manufacturer;

    /** 设备型号 */
    @TableField(value = "model")
    @AutoColumn(comment = "设备型号", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String model;

    /** 安装地址 */
    @TableField(value = "address")
    @AutoColumn(comment = "安装地址", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String address;

    /** 字符编码：GB2312/UTF-8 */
    @TableField(value = "character_set")
    @AutoColumn(comment = "字符编码", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String characterSet;

    /** 是否支持云台控制：0-不支持，1-支持 */
    @TableField(value = "ptz")
    @AutoColumn(comment = "是否支持云台控制", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer ptz;

    /** 是否启用RTCP：0-否，1-是 */
    @TableField(value = "rtcp")
    @AutoColumn(comment = "是否启用RTCP", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtcp;

    /** 状态：0-离线，1-在线 */
    @TableField(value = "status")
    @AutoColumn(comment = "状态", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer status;

    /** 目录分组 */
    @TableField(value = "catalog_group")
    @AutoColumn(comment = "目录分组", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer catalogGroup;

    /** 注册方式：1-IP注册，2-动态域名，3-主动上报 */
    @TableField(value = "register_way")
    @AutoColumn(comment = "注册方式", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer registerWay;

    /** 保密方式 */
    @TableField(value = "safety_way")
    @AutoColumn(comment = "保密方式", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer safetyWay;

    /** 保密属性 */
    @TableField(value = "secrecy")
    @AutoColumn(comment = "保密属性", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer secrecy;

    /** 创建时间 */
    @TableField(value = "create_time")
    @AutoColumn(comment = "创建时间", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    @AutoColumn(comment = "更新时间", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String updateTime;

    /** 是否作为消息通道：0-否，1-是 */
    @TableField(value = "as_message_channel")
    @AutoColumn(comment = "是否作为消息通道", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Integer asMessageChannel;

    /** 是否查询平台目录：0-否，1-是 */
    @TableField(value = "catalog_with_platform")
    @AutoColumn(comment = "是否查询平台目录", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer catalogWithPlatform;

    /** 是否查询分组目录：0-否，1-是 */
    @TableField(value = "catalog_with_group")
    @AutoColumn(comment = "是否查询分组目录", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer catalogWithGroup;

    /** 是否查询区域目录：0-否，1-是 */
    @TableField(value = "catalog_with_region")
    @AutoColumn(comment = "是否查询区域目录", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer catalogWithRegion;

    /** 是否自动推送通道：0-否，1-是 */
    @TableField(value = "auto_push_channel")
    @AutoColumn(comment = "是否自动推送通道", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer autoPushChannel;

    /** 推流IP地址 */
    @TableField(value = "send_stream_ip")
    @AutoColumn(comment = "推流IP地址", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String sendStreamIp;

    /** 流媒体服务器ID */
    @TableField(value = "server_id")
    @AutoColumn(comment = "流媒体服务器ID", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverId;
}
