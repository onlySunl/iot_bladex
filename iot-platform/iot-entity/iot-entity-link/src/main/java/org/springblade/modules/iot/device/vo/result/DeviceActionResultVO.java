package org.springblade.modules.iot.device.vo.result;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单查询方法返回值VO
 * 设备动作数据
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceActionResultVO", description = "设备动作数据")
public class DeviceActionResultVO extends AuditableResultVO {

    private static final long serialVersionUID = 1L;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
     * 动作类型
     */
    @Schema(description = "动作类型")
    private String actionType;
    /**
     * 内容信息
     */
    @Schema(description = "内容信息")
    private String message;
    /**
     * 状态:0=成功 / 1=失败
     */
    /**
     * 备注
     */

}
