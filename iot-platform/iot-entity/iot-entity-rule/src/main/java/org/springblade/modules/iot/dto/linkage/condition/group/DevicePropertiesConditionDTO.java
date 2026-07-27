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
 * File Name: DevicePropertiesConditionDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 设备属性条件
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
 * @date 2023-11-26 22:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DevicePropertiesConditionDTO", description = "单个条件或子条件组")
public class DevicePropertiesConditionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识符", example = "952BDB3C-E8D8-40B7-A5A5-274852A659B31700995443181")
    private String uuid;

    @Schema(description = "条件类型", example = "CONDITION")
    private String type;

    @Schema(description = "逻辑运算符", example = "AND")
    private String logicalOperator;

    @Schema(description = "左操作数参数")
    private DevicePropertiesLeftParamDTO leftParam;

    @Schema(description = "运算符")
    private OperatorDTO operator;

    @Schema(description = "右操作数参数集合")
    private List<DevicePropertiesRightParamsDTO> rightParams;

}
