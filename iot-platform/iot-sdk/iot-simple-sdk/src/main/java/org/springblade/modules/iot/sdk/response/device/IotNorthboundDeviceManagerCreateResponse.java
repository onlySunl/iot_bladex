package org.springblade.modules.iot.sdk.response.device;

import lombok.Data;

/**
 * Description:
 * 物联网北向API-设备管理创建响应参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/23
 */
@Data
public class IotNorthboundDeviceManagerCreateResponse {
    /**
     * 设备标识
     */
    private String deviceIdentification;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 客户端标识
     */
    private String clientId;
    /**
     * 认证方式: 0-用户名密码，1-SSL证书
     */
    private Integer authMode;
    /**
     * 设备类型: 0-普通设备，1-网关设备，2-子设备
     */
    private Integer nodeType;
    /**
     * 产品标识
     */
    private String productIdentification;
    /**
     * 设备状态: 0-未激活，1-已启用，2-已禁用
     */
    private Integer deviceStatus;
    /**
     * 连接状态: 0-未连接，1-在线，2-离线
     */
    private Integer connectStatus;
    /**
     * 软件版本
     */
    private String swVersion;
    /**
     * 固件版本
     */
    private String fwVersion;
    /**
     * SDK版本
     */
    private String deviceSdkVersion;
    /**
     * 网关设备ID
     */
    private String gatewayId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 加密方式: 0-明文传输，1-SM4，2-AES
     */
    private Integer encryptMethod;
    /**
     * 设备描述
     */
    private String description;
    /**
     * 备注
     */
    private String remark;
}
