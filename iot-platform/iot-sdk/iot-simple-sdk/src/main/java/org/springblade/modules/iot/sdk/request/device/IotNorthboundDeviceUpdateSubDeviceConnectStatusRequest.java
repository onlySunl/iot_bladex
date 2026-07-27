package org.springblade.modules.iot.sdk.request.device;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-修改子设备连接状态请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceUpdateSubDeviceConnectStatusRequest {

    /**
     * 网关设备标识
     * @mock GATEWAY_001
     */
    private String gatewayIdentification;

    /**
     * 子设备状态列表
     */
    private List<DeviceStatus> deviceStatuses;

    /**
     * 子设备状态数据模型
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceStatus {

        /**
         * 子设备唯一标识
         * @mock DEVICE_001
         */
        private String deviceId;

        /**
         * 子设备状态：ONLINE-在线、OFFLINE-离线、INIT-未连接
         * @mock ONLINE
         */
        private String status;
    }
}
