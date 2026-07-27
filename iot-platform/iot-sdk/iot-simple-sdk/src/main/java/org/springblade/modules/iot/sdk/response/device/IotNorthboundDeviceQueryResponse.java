package org.springblade.modules.iot.sdk.response.device;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-查询设备信息响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceQueryResponse {

    /**
     * 请求处理的结果码：0表示成功，非0表示失败
     * @mock 0
     */
    private Integer statusCode;

    /**
     * 响应状态描述
     * @mock 查询成功
     */
    private String statusDesc;

    /**
     * 设备信息列表
     */
    private List<DeviceItem> data;

    /**
     * 设备信息数据模型
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceItem {

        /**
         * 请求处理的结果码
         */
        private Integer statusCode;

        /**
         * 响应状态描述
         */
        private String statusDesc;

        /**
         * 设备ID
         */
        private String deviceId;

        /**
         * 设备详细信息
         */
        private DeviceInfo deviceInfo;
    }

    /**
     * 设备详细信息数据模型
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceInfo {

        /**
         * 客户端标识
         */
        private String clientId;

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
         * 设备状态：0-未激活、1-已启用、2-已禁用
         */
        private Integer deviceStatus;

        /**
         * 连接状态：0-未连接、1-在线、2-离线
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
         * SDK版本
         */
        private String deviceSdkVersion;

        /**
         * 网关设备ID
         */
        private String gatewayId;

        /**
         * 设备类型：0-普通设备、1-网关设备、2-子设备
         */
        private Integer nodeType;

        /**
         * 备注
         */
        private String remark;
    }
}
