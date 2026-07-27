package org.springblade.modules.iot.dto.linkage.condition.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: DevicePropertiesParameterDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 设备属性右操作参数定义
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
 * @date 2023-11-26 22:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DevicePropertiesRightParamsDTO", description = "参数定义，用于条件比较的右操作参数数据")
public class DevicePropertiesRightParamsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "类型", example = "CONSTANT")
    private String type;

    @Schema(description = "值", example = "123")
    private String value;

    @Schema(description = "描述", example = "test1")
    private String desc;
}
