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
 * @Description: 设备数据上报数据模型
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
@Schema(title = "TopoDeviceDataReportParam", description = "设备数据上报数据模型")
public class TopoDeviceDataReportParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "设备数据")
    @NotNull(message = "设备数据不能为空")
    private List<DeviceS> devices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(title = "DeviceS", description = "设备数据模型")
    public static class DeviceS implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "设备唯一标识")
        @NotEmpty(message = "设备唯一标识不能为空")
        private String deviceId;

        @Schema(description = "服务列表")
        @NotNull(message = "服务列表不能为空")
        private List<Services> services;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @Schema(title = "Services", description = "服务数据模型")
        public static class Services implements Serializable {
            @Serial
            private static final long serialVersionUID = 1L;

            @Schema(description = "服务编码")
            @NotEmpty(message = "服务编码不能为空")
            private String serviceCode;

            @Schema(description = "服务数据")
            @NotNull(message = "服务数据不能为空")
            private Object data;

            @Schema(description = "事件时间")
            @NotNull(message = "事件时间不能为空")
            private Long eventTime;
        }
    }
}
