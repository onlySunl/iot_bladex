package org.springblade.modules.iot.protocol.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @program: iot-platform
 * @description: 协议添加网关子设备响应信息
 * @packagename: org.springblade.modules.iot.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-18 23:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "TopoAddDeviceResultVO", description = "网关子设备响应信息")
public class TopoAddDeviceResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "请求处理的结果码。'0'表示成功。非'0'表示失败。详见附录。", requiredMode = Schema.RequiredMode.REQUIRED)
    private int statusCode;

    @Schema(description = "响应状态描述。")
    private String statusDesc;

    @Schema(description = "添加子设备的结果信息。", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<DataItem> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DataItem {

        @Schema(description = "请求处理的结果码。'0'表示成功。非'0'表示失败。详见附录。", requiredMode = Schema.RequiredMode.REQUIRED)
        private int statusCode;

        @Schema(description = "响应状态描述。")
        private String statusDesc;

        @Schema(description = "设备详细信息")
        private DeviceInfo deviceInfo;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class DeviceInfo {

            @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
            private String name;

            @Schema(description = "厂商ID", requiredMode = Schema.RequiredMode.REQUIRED)
            private String manufacturerId;

            @Schema(description = "设备描述")
            private String description;

            @Schema(description = "设备型号")
            private String model;

            @Schema(description = "平台生成的设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
            private String deviceId;

            @Schema(description = "设备自身的唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
            private String nodeId;
        }
    }
}