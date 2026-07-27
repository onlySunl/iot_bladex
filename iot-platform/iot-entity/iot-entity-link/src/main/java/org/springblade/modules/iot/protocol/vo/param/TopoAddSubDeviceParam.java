package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
 * @Description: 网关设备添加子设备数据模型
 * @Author: ShiHuan SUN
 * @E-mail: 13733918655@163.com
 * @Website: https://mqttsnet.com
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
@Schema(title = "TopoAddDeviceSaveVO", description = "网关设备添加子设备数据模型")
public class TopoAddSubDeviceParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "网关设备标识")
    @NotEmpty(message = "网关设备标识不能为空")
    private String gatewayIdentification;

    @Schema(description = "子设备信息集合")
    @NotNull(message = "子设备信息集合不能为空")
    private List<DeviceInfos> deviceInfos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeviceInfos {

        @Schema(description = "子设备ID")
        private String nodeId;

        @Schema(description = "子设备名称")
        private String name;

        @Schema(description = "子设备描述")
        private String description;

        @Schema(description = "子设备厂商ID")
        private String manufacturerId;

        @Schema(description = "子设备型号")
        private String model;
    }
}
