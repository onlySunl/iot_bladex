package org.springblade.modules.iot.sdk.response.device;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Description:
 * 北向API-下发设备命令响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/01/22
 */
@Data
@Builder
public class IotNorthboundDeviceIssueCommandResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 命令下发结果列表（与请求中的命令顺序对应）
     */
    private List<CommandResultItem> commandResults;

    /**
     * 单条命令下发结果
     */
    @Data
    @Builder
    public static class CommandResultItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 设备标识
         */
        private String deviceIdentification;

        /**
         * 命令标识
         * @mock CMD-20250122-001
         */
        private String commandIdentification;

        /**
         * 命令类型(0:命令下发、1:命令响应)
         */
        private Integer commandType;

        /**
         * 状态(0:待下发、1:下发成功、2:下发失败)
         */
        private Integer status;

        /**
         * 内容（命令执行结果或错误信息）
         */
        private String content;

        /**
         * 备注
         */
        private String remark;
    }
}
