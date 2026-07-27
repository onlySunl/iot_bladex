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

/**
 * <p>
 * 表单查询条件VO
 * 设备命令下发及响应表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceCommandPageQuery", description = "设备命令下发及响应表")
public class DeviceCommandPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
     * 命令标识
     */
    @Schema(description = "命令标识")
    private String commandIdentification;
    /**
     * 命令类型(0:命名下发、1:命令响应)
     */
    @Schema(description = "命令类型(0:命名下发、1:命令响应)")
    private Integer commandType;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;
    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
