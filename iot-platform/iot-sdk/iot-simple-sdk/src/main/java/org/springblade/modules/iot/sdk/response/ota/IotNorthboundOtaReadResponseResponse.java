package org.springblade.modules.iot.sdk.response.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA读取设备软固件版本信息响应结果
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaReadResponseResponse {

    /**
     * 操作结果：true-成功、false-失败
     */
    private Boolean success;

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

    /**
     * 响应消息
     */
    private String message;
}
