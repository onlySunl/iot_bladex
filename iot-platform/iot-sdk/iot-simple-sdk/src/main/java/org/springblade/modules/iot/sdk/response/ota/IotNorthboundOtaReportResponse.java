package org.springblade.modules.iot.sdk.response.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA上报软固件版本响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaReportResponse {
    /**
     * 设备唯一标识
     */
    private String deviceIdentification;

    /**
     * OTA包类型：0-软件包、1-固件包
     */
    private Integer packageType;

    /**
     * 当前版本号
     */
    private String currentVersion;
}
