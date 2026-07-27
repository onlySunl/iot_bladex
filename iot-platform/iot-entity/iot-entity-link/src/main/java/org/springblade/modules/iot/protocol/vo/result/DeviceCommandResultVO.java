package org.springblade.modules.iot.protocol.vo.result;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceCommandResultVO", description = "设备命令下发及响应表")
public class DeviceCommandResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

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
    @Schema(description = "命令类型(0:命令下发、1:命令响应)")
    private Integer commandType;
    /**
     * 状态
     */
    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
    /**
     * 备注
     */

}
