package org.springblade.modules.iot.sdk.request.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA获取可升级版本列表请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaListUpgradeableVersionsRequest {

    /**
     * 设备唯一标识
     */
    private String deviceIdentification;

    /**
     * OTA包类型：0-软件包、1-固件包
     */
    private Integer packageType;
}
