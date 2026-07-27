package org.springblade.modules.iot.device.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 设备二维码信息结果VO
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-20 14:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "DeviceQrcodeResultVO", description = "设备二维码信息结果VO")
public class DeviceQrcodeResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 场景(QrcodeConstant)
     */
    @Schema(description = "场景(deviceScene)")
    private String scene;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;

    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;

}
