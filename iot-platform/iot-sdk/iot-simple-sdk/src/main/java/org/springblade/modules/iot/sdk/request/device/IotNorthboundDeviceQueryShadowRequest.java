package org.springblade.modules.iot.sdk.request.device;

import lombok.Data;

/**
 * Description:
 * 北向API-查询设备影子请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/01/22
 */
@Data
public class IotNorthboundDeviceQueryShadowRequest {

    /**
     * 设备标识（设备的唯一标识，如SN码/IMEI等）
     * @mock SN-2025-ABCD-5678
     */
    private String deviceIdentification;

    /**
     * 开始时间戳（选填，不指定默认查询最新的一条）
     * 格式：19位纳秒时间戳
     * @mock 1622552643000000000
     */
    private Long startTime;

    /**
     * 结束时间戳（选填，不指定默认查询最新的一条）
     * 格式：19位纳秒时间戳
     * @mock 1622552644000000000
     */
    private Long endTime;

    /**
     * 服务编码（选填，不指定则查询所有服务）
     * @mock service001
     */
    private String serviceCode;

}
