package org.springblade.modules.iot.dto.linkage.condition.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceActionConditionGroupDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 设备属性条件组
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024-07-26 15:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceActionConditionGroupDTO", description = "设备动作条件组，可以包含多个条件")
public class DeviceActionConditionGroupDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "条件类型", example = "GROUP")
    private String type;

    @Schema(description = "唯一标识符", example = "0D4B68A2-EF28-489B-9478-A3DAB4665A721700995443181")
    private String uuid;

    @Schema(description = "逻辑运算符", example = "AND")
    private String logicalOperator;

    @Schema(description = "条件集合")
    private List<DeviceActionConditionDTO> conditions;

}
