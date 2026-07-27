package org.springblade.modules.iot.device.vo.save;

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

/**
 * <p>
 * 表单保存方法VO
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
@Schema(title = "DeviceCommandSaveVO", description = "设备命令下发及响应表")
public class DeviceCommandSaveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    @NotEmpty(message = "请填写设备标识")
    @Size(max = 255, message = "设备标识长度不能超过{max}")
    private String deviceIdentification;

    /**
     * 命令类型(0:命名下发、1:命令响应)
     */
    @Schema(description = "命令类型(0:命名下发、1:命令响应)")
    @NotNull(message = "请填写命令类型(0:命名下发、1:命令响应)")
    private Integer commandType;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "请填写状态")
    private Integer status;
    /**
     * 内容
     */
    @Schema(description = "内容")
    @Size(max = 2147483647, message = "内容长度不能超过{max}")
    private String content;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
