package org.springblade.modules.iot.sdk.response.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * 北向API-修改设备状态响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceUpdateStatusResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 操作结果：true-成功、false-失败
     * @mock true
     */
    private Boolean success;

    /**
     * 设备标识
     * @mock DEVICE_001
     */
    private String deviceIdentification;

    /**
     * 设备名称
     * @mock 测试设备
     */
    private String deviceName;

    /**
     * 客户端标识
     * @mock CLIENT_001
     */
    private String clientId;

    /**
     * 用户名
     * @mock admin
     */
    private String userName;

    /**
     * 应用ID
     * @mock APP_001
     */
    private String appId;

    /**
     * 更新前的设备状态：0-未激活、1-已激活、2-已禁用
     * @mock 0
     */
    private Integer previousStatus;

    /**
     * 更新后的设备状态：0-未激活、1-已激活、2-已禁用
     * @mock 1
     */
    private Integer currentStatus;

    /**
     * 连接状态：0-未连接、1-在线、2-离线
     * @mock 1
     */
    private Integer connectStatus;

    /**
     * 设备类型：0-普通设备、1-网关设备、2-子设备
     * @mock 0
     */
    private Integer nodeType;

    /**
     * 产品标识
     * @mock PROD_001
     */
    private String productIdentification;

    /**
     * 产品名称
     * @mock 智能传感器
     */
    private String productName;

    /**
     * 软件版本
     * @mock v1.0.0
     */
    private String swVersion;

    /**
     * 固件版本
     * @mock v1.0.0
     */
    private String fwVersion;

    /**
     * SDK版本
     * @mock v1
     */
    private String deviceSdkVersion;

    /**
     * 设备描述
     * @mock 测试设备描述
     */
    private String description;

    /**
     * 网关设备ID（子设备时填写）
     * @mock GATEWAY_001
     */
    private String gatewayId;

    /**
     * 备注
     * @mock 设备备注信息
     */
    private String remark;

    /**
     * 状态更新时间
     * @mock 2026-02-02T12:00:00
     */
    private LocalDateTime updateTime;

}
