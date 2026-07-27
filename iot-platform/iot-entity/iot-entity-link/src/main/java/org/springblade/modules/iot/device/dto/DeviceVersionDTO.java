package org.springblade.modules.iot.device.dto;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "DeviceVersionDTO", description = "设备软固件版本集合DTO")
public class DeviceVersionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;

    /**
     * 软件版本列表（逗号分隔）
     */
    @Schema(description = "软件版本号集合")
    private String swVersions;

    /**
     * 固件版本列表（逗号分隔）
     */
    @Schema(description = "固件版本号集合")
    private String fwVersions;
}