package org.springblade.modules.iot.sdk.response.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-保存OTA升级记录响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaSaveUpgradeRecordResponse {

    /**
     * 操作结果：true-成功、false-失败
     * @mock true
     */
    private Boolean success;

    /**
     * 设备唯一标识
     * @mock DEVICE_001
     */
    private String deviceIdentification;

    /**
     * OTA任务ID
     * @mock 123456
     */
    private Long otaTaskId;

    /**
     * 升级状态：0-待升级、1-升级中、2-升级成功、3-升级失败
     * @mock 2
     */
    private Integer upgradeStatus;

    /**
     * 升级进度（百分比）
     * @mock 100
     */
    private Integer progress;
}
