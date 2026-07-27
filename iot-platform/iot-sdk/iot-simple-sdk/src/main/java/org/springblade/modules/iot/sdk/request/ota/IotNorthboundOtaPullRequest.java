package org.springblade.modules.iot.sdk.request.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA拉取软固件信息请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaPullRequest {

    /**
     * 设备唯一标识
     * @mock DEVICE_001
     */
    private String deviceIdentification;

    /**
     * OTA包类型：0-软件包、1-固件包
     * @mock 1
     */
    private Integer packageType;

    /**
     * 当前版本号
     * @mock v1.0.0
     */
    private String currentVersion;

    /**
     * 请求版本号（可选，不填则获取最新版本）
     * @mock v2.0.0
     */
    private String requestVersion;
}
