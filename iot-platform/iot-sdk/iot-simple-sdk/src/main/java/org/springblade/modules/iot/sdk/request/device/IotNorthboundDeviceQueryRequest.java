package org.springblade.modules.iot.sdk.request.device;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-查询设备信息请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundDeviceQueryRequest {

    /**
     * 设备标识集合
     * @mock ["DEVICE_001","DEVICE_002"]
     */
    private List<String> deviceIds;
}
