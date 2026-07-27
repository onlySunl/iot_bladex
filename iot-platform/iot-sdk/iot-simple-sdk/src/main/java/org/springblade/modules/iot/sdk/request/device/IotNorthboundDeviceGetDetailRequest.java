package org.springblade.modules.iot.sdk.request.device;

import lombok.Data;

/**
 * Description:
 * 北向API-查询设备详情请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/01/22
 */
@Data
public class IotNorthboundDeviceGetDetailRequest {

    /**
     * 设备标识（设备的唯一标识，如SN码/IMEI等）
     * @mock SN-2025-ABCD-5678
     */
    private String deviceIdentification;

}
