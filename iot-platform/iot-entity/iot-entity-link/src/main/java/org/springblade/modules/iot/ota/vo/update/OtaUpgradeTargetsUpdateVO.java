package org.springblade.modules.iot.ota.vo.update;

import org.springblade.basic.base.entity.Entity;
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
 * 表单修改方法VO
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(description = "OTA升级目标表")
public class OtaUpgradeTargetsUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = Entity.Update.class)
    private Long id;

    /**
     * 任务ID
     */
    @Schema(description = "任务ID")
    @NotNull(message = "请填写任务ID")
    private Long taskId;
    /**
     * 目标值(设备标识/分组ID/省市区域编码)
     */
    @Schema(description = "目标值(设备标识/分组ID/省市区域编码)")
    @NotEmpty(message = "请填写目标值(设备标识/分组ID/省市区域编码)")
    @Size(max = 100, message = "目标值(设备标识/分组ID/省市区域编码)长度不能超过{max}")
    private String targetValue;
    /**
     * 目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)
     */
    @Schema(description = "目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)")
    @NotNull(message = "请填写目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)")
    private Integer targetStatus;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 逻辑删除标识(0-未删除、1-已删除)
     */
    @Schema(description = "逻辑删除标识(0-未删除、1-已删除)")
    @NotNull(message = "请填写逻辑删除标识(0-未删除、1-已删除)")
    private Integer deleted;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
