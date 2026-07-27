package org.springblade.modules.iot.sdk.request.ota;

import java.util.List;

import lombok.Data;

/**
 * Description:
 * 物联网北向API-升级任务拒绝请求参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/1/6
 */
@Data
public class IotNorthboundOtaRejectTaskRequest {

    /**
     * 升级任务ID
     * 必填参数，不能为空
     */
    private Long taskId;

    /**
     * 设备标识列表
     * 必填参数，不能为空且最多100个设备标识
     * 用于批量拒绝处于待确认状态的设备升级作业
     */
    private List<String> deviceIdentificationList;
}
