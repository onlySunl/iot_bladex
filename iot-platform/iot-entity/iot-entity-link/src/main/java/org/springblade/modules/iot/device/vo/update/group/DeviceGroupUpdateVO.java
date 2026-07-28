package org.springblade.modules.iot.device.vo.update.group;

import org.springblade.basic.base.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceGroupUpdateVO", description = "设备分组")
public class DeviceGroupUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "请填写id", groups = Entity.Update.class)
    private Long id;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 64, message = "应用ID长度不能超过{max}")
    private String appId;
    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    @Size(max = 255, message = "分组名称长度不能超过{max}")
    private String groupName;
    /**
     * 分组类型
     */
    @Schema(description = "分组类型")
    @NotNull(message = "请填写分组类型")
    private Integer type;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    @NotNull(message = "请填写状态")
    private Boolean state;
    /**
     * 分组描述
     */
    @Schema(description = "分组描述")
    @Size(max = 500, message = "分组描述长度不能超过{max}")
    private String description;
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

    @Schema(description = "父节点")
    protected Long parentId;

    @Schema(description = "排序号")
    protected Integer sortValue;

}
