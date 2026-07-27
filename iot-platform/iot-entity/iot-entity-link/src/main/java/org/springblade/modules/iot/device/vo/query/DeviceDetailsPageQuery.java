package org.springblade.modules.iot.device.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * <p>
 * 设备档案详情信息
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceDetailsPageQuery", description = "设备详情信息 page query")
public class DeviceDetailsPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;

    /**
     * 设备标识集合
     */
    @Schema(description = "设备标识集合")
    private List<String> deviceIdentificationList;
    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String deviceName;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;
    /**
     * 网关设备id
     */
    @Schema(description = "网关设备id")
    private String gatewayId;

    /**
     * 网关设备id集合
     */
    @Schema(description = "网关设备id集合")
    private List<String> gatewayIdList;
    /**
     * 设备类型:0普通设备 || 1网关设备 || 2子设备
     */
    @Schema(description = "设备类型:0普通设备 || 1网关设备 || 2子设备 ")
    private Integer nodeType;

}
