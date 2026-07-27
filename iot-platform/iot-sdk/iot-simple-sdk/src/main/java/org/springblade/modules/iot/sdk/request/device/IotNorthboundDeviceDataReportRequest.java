package org.springblade.modules.iot.sdk.request.device;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-设备数据上报请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceDataReportRequest {

    /**
     * 设备数据列表
     */
    private List<DeviceData> devices;

    /**
     * 设备数据模型
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceData {

        /**
         * 设备唯一标识
         * @mock DEVICE_001
         */
        private String deviceId;

        /**
         * 服务列表
         */
        private List<ServiceData> services;
    }

    /**
     * 服务数据模型
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceData {

        /**
         * 服务编码
         * @mock temperature
         */
        private String serviceCode;

        /**
         * 服务数据（JSON格式）
         * @mock {"value":25.5}
         */
        private Object data;

        /**
         * 事件时间戳（毫秒）
         * @mock 1706832000000
         */
        private Long eventTime;
    }
}
