package org.springblade.modules.iot.sdk.request.device;

import lombok.Data;

/**
 * Description:
 * 北向API-修改设备状态请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
public class IotNorthboundDeviceUpdateStatusRequest {

    /**
     * 设备标识（设备的唯一标识）
     * @mock DEVICE_001
     */
    private String deviceIdentification;

    /**
     * 设备状态：0-未激活、1-已激活、2-已禁用
     * @mock 1
     */
    private Integer status;

}
