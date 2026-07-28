package org.springblade.modules.iot.device.vo.update.group;

import org.springblade.basic.base.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(description = "设备分组关系")
public class DeviceGroupRelUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "请填写id", groups = Entity.Update.class)
    private Long id;

    /**
     * 分组ID;#device_group
     */
    @Schema(description = "分组ID")
    @NotNull(message = "请填写分组ID")
    private Long groupId;
    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    @Size(max = 255, message = "设备标识长度不能超过{max}")
    private String deviceIdentification;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 最后修改时间
     */
    @Schema(description = "最后修改时间")
    private LocalDateTime updatedTime;
    /**
     * 最后修改人
     */
    @Schema(description = "最后修改人")
    private Long updatedBy;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
