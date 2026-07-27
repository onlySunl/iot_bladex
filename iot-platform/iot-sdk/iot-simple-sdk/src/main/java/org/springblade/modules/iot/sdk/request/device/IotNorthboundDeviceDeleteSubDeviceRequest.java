package org.springblade.modules.iot.sdk.request.device;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-删除子设备请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceDeleteSubDeviceRequest {

    /**
     * 网关设备标识
     * @mock GATEWAY_001
     */
    private String gatewayIdentification;

    /**
     * 子设备标识集合
     * @mock ["DEVICE_001","DEVICE_002"]
     */
    private List<String> deviceIds;
}
