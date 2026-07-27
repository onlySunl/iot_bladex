package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceAlarmNotificationRequestParam
 * -----------------------------------------------------------------------------
 * Description:
 * 设备告警通知请求参数
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/5/31       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/5/31 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "DeviceAlarmNotificationRequestParam", description = "Device Alarm Notification Request Data Structure")
public class DeviceAlarmNotificationRequestParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "告警标识")
    private String alarmIdentification;

    @Schema(description = "告警内容")
    private String contentData;

    @Schema(description = "告警通知手机号码（多个用逗号分割）")
    private String atPhone;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;

}
