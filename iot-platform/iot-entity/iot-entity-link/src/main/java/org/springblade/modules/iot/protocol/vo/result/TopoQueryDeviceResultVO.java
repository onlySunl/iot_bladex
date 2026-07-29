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
 * @description: 协议查询设备档案信息响应信息
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
@Schema(title = "TopoQueryDeviceResultVO", description = "设备档案响应信息")
public class TopoQueryDeviceResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "请求处理的结果码。'0'表示成功。非'0'表示失败。详见附录。", requiredMode = Schema.RequiredMode.REQUIRED)
    private int statusCode;

    @Schema(description = "响应状态描述。")
    private String statusDesc;

    @Schema(description = "查询设备的结果信息。", requiredMode = Schema.RequiredMode.REQUIRED)
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

        @Schema(description = "平台生成的设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
        private String deviceId;

        @Schema(description = "设备详细信息")
        private DeviceInfo deviceInfo;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class DeviceInfo {

            /**
             * 客户端标识
             */
            @Schema(description = "客户端标识")
            private String clientId;

            /**
             * 设备名称
             */
            @Schema(description = "设备名称")
            private String deviceName;
            /**
             * 连接实例
             */
            @Schema(description = "连接实例")
            private String connector;
            /**
             * 设备描述
             */
            @Schema(description = "设备描述")
            private String description;
            /**
             * 设备状态:1启用ENABLE || 2禁用DISABLE||未激活NOTACTIVE 0
             */
            @Schema(description = "设备状态:1启用ENABLE || 2禁用DISABLE||未激活NOTACTIVE 0")
            private Integer deviceStatus;
            /**
             * 连接状态:在线：1ONLINE || 离线：2OFFLINE || 未连接：INIT 0
             */
            @Schema(description = "连接状态:在线：1ONLINE || 离线：2OFFLINE || 未连接：INIT 0")
            private Integer connectStatus;
            /**
             * 设备标签
             */
            @Schema(description = "设备标签")
            private String deviceTags;
            /**
             * 产品标识
             */
            @Schema(description = "产品标识")
            private String productIdentification;
            /**
             * 软件版本
             */
            @Schema(description = "软件版本")
            private String swVersion;
            /**
             * 固件版本
             */
            @Schema(description = "固件版本")
            private String fwVersion;
            /**
             * sdk版本
             */
            @Schema(description = "sdk版本")
            private String deviceSdkVersion;
            /**
             * 网关设备id
             */
            @Schema(description = "网关设备id")
            private String gatewayId;
            /**
             * 设备类型:0普通设备 || 1网关设备 || 2子设备
             */
            @Schema(description = "设备类型:0普通设备 || 1网关设备 || 2子设备 ")
            private Integer nodeType;
            /**
             * 备注
             */
            @Schema(description = "备注")
            private String remark;
        }
    }
}