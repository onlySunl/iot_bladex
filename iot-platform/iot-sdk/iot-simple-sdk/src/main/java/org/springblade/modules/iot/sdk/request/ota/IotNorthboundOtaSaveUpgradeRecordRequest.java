package org.springblade.modules.iot.sdk.request.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-保存OTA升级记录请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaSaveUpgradeRecordRequest {

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

    /**
     * 升级开始时间戳（毫秒）
     * @mock 1706832000000
     */
    private Long startTime;

    /**
     * 升级结束时间戳（毫秒）
     * @mock 1706835600000
     */
    private Long endTime;

    /**
     * 错误码
     * @mock 0
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 成功详情
     */
    private String successDetails;

    /**
     * 失败详情
     */
    private String failureDetails;

    /**
     * 升级日志详情
     */
    private String logDetails;
}
