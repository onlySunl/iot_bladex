package org.springblade.modules.iot.sdk.request.device;

import lombok.Data;

/**
 * Description:
 * 物联网北向API-设备管理创建请求参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/23
 */
@Data
public class IotNorthboundDeviceManagerCreateRequest {

    /**
     * 客户端标识
     */
    private String clientId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 认证方式0-用户名密码，1-ssl证书
     *
     * @mock 0
     */
    private Integer authMode;
    /**
     * 加密密钥
     */
    private String encryptKey;
    /**
     * 加密向量
     */
    private String encryptVector;
    /**
     * 签名密钥
     */
    private String signKey;
    /**
     * 传输协议的加密方式：0-明文传输、1-SM4、2-AES
     *
     * @mock 0
     */
    private Integer encryptMethod;
    /**
     * 设备唯一标识（SN码/IMEI）
     * 必须保证唯一
     *
     * @mock "SN-2025-ABCD-5678"
     */
    private String deviceIdentification;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 连接实例
     */
    private String connector;
    /**
     * 设备描述
     */
    private String description;
    /**
     * 设备状态:1-启用ENABLE || 2-禁用DISABLE||0-未激活NOTACTIVE
     */
    private Integer deviceStatus;
    /**
     * 连接状态:在线：1-ONLINE || 离线：2-OFFLINE || 未连接：0-INIT
     */
    private Integer connectStatus;
    /**
     * 设备标签
     */
    private String deviceTags;
    /**
     * 产品标识
     */
    private String productIdentification;
    /**
     * 软件版本
     */
    private String swVersion;
    /**
     * 固件版本
     */
    private String fwVersion;
    /**
     * sdk版本
     */
    private String deviceSdkVersion;
    /**
     * 网关设备id
     * 如果设备类型是子设备,必须填写父(网关)设备的设备标识
     */
    private String gatewayId;

    /**
     * 设备类型:0-普通设备 || 1-网关设备 || 2-子设备
     *
     * @mock 0
     */
    private Integer nodeType;

    /**
     * 备注
     */
    private String remark;
}
