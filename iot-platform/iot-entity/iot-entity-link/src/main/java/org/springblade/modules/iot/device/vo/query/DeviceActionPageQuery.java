package org.springblade.modules.iot.device.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 * 表单查询条件VO
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
@Schema(title = "DeviceActionPageQuery", description = "设备动作数据")
public class DeviceActionPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

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
     * 状态
     */
    @Schema(description = "状态", example = "0", allowableValues = "0,1")
    private Integer status;
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
