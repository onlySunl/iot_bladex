package org.springblade.modules.iot.device.vo.update;

import org.springblade.basic.base.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 表单修改方法VO
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
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceActionUpdateVO", description = "设备动作数据")
public class DeviceActionUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "请填写id", groups = Entity.Update.class)
    private Long id;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    @Size(max = 255, message = "设备标识长度不能超过{max}")
    private String deviceIdentification;
    /**
     * 动作类型
     */
    @Schema(description = "动作类型")
    @Size(max = 255, message = "动作类型长度不能超过{max}")
    private String actionType;
    /**
     * 内容信息
     */
    @Schema(description = "内容信息")
    @Size(max = 2147483647, message = "内容信息长度不能超过{max}")
    private String message;
    /**
     * 状态
     */
    @Schema(description = "状态", example = "0", allowableValues = "0,1")
    @NotNull(message = "状态不能为空")
    private Integer status;
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
