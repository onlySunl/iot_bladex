package org.springblade.modules.iot.protocol.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @program: thinglinks-cloud
 * @description: 设备操作结果数据模型
 * @packagename: com.mqttsnet.thinglinks.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-22 14:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "TopoDeviceOperationResultVO", description = "设备操作结果数据ResultVO")
public class TopoDeviceOperationResultVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "请求处理的结果码")
    private Integer statusCode;

    @Schema(description = "响应状态描述", name = "响应状态描述")
    private String statusDesc;

    @Schema(description = "操作结果信息")
    private List<OperationRsp> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(title = "OperationRsp", description = "操作结果结果数据模型")
    public static class OperationRsp implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "设备ID")
        private String deviceId;

        @Schema(description = "请求处理的结果码")
        private Integer statusCode;

        @Schema(description = "响应状态描述", name = "响应状态描述")
        private String statusDesc;
    }
}