package org.springblade.modules.iot.protocol.vo.param;

import org.springblade.modules.iot.device.enumeration.DeviceConnectStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 网关设备更新子设备状态数据模型
 * @Author: ShiHuan SUN
 * @E-mail: 13733918655@163.com
 * @Website: <a href="https://mqttsnet.com">官方地址</a>
 * @CreateDate: 2022/4/25$ 12:52$
 * @UpdateUser: ShiHuan SUN
 * @UpdateDate: 2022/4/25$ 12:52$
 * @UpdateRemark: 修改内容
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "TopoUpdateSubDeviceStatusParam", description = "子设备状态更新数据模型")
public class TopoUpdateSubDeviceStatusParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "网关设备标识")
    @NotEmpty(message = "网关设备标识不能为空")
    private String gatewayIdentification;

    @Schema(description = "子设备状态列表")
    @NotNull(message = "子设备状态列表不能为空")
    @Size(min = 1, max = 100, message = "子设备状态列表大小必须在1到100之间")
    private List<DeviceStatus> deviceStatuses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(title = "DeviceStatus", description = "子设备状态数据模型")
    public static class DeviceStatus implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "子设备唯一标识")
        @NotEmpty(message = "子设备唯一标识不能为空")
        private String deviceId;

        @Schema(description = "子设备状态")
        @NotNull(message = "子设备状态不能为空")
        private DeviceConnectStatusEnum status;
    }
}
