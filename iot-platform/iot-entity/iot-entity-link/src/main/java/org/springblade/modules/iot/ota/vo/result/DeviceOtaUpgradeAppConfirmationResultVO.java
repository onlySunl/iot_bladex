package org.springblade.modules.iot.ota.vo.result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Description:
 * OTA升级确认响应参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/1/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "OtaUpgradeRecordsResultVO", description = "OTA升级记录表")
public class DeviceOtaUpgradeAppConfirmationResultVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 升级任务ID
     */
    private Long taskId;
    /**
     * 本次操作成功的设备标识列表
     */
    private List<String> deviceIdentificationList;
}
