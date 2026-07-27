package org.springblade.modules.iot.sdk.response.device;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * Description:
 * 北向API-查询设备详情响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/01/22
 */
@Data
@Builder
public class IotNorthboundDeviceGetDetailResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
     * 用户名
     */
    private String userName;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 认证方式：0-用户名密码，1-SSL证书
     */
    private Integer authMode;

    /**
     * 传输协议的加密方式：0-明文传输、1-SM4、2-AES
     */
    private Integer encryptMethod;

    /**
     * 设备状态:1-启用ENABLE || 2-禁用DISABLE||0-未激活NOTACTIVE
     */
    private Integer deviceStatus;

    /**
     * 连接状态:在线：1-ONLINE || 离线：2-OFFLINE || 未连接：0-INIT
     */
    private Integer connectStatus;

    /**
     * 设备类型:0-普通设备 || 1-网关设备 || 2-子设备
     */
    private Integer nodeType;

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
     * SDK版本
     */
    private String deviceSdkVersion;

    /**
     * 设备描述
     */
    private String description;

    /**
     * 设备标签
     */
    private String deviceTags;

    /**
     * 网关设备ID（子设备时填写）
     */
    private String gatewayId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后连接时间
     */
    private LocalDateTime lastConnectTime;

    /**
     * 最后心跳时间
     */
    private LocalDateTime lastHeartbeatTime;

}
