package org.springblade.modules.iot.sdk.response.ota;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Description:
 * 物联网北向API-OTA升级拒绝响应参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/1/6
 */
@Data
@Builder
public class IotNorthboundOtaRejectTaskResponse {
    /**
     * 升级任务ID
     */
    private Long taskId;
    /**
     * 拒绝成功的设备标识列表
     */
    private List<String> deviceIdentificationList;
}
