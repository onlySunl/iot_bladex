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

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceActionLeftParamDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 设备属性左操作参数定义
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
 * @date 2024-07-26 15:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceActionLeftParamDTO", description = "参数定义，用于条件比较的左操作参数数据")
public class DeviceActionLeftParamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "参数ID", example = "")
    private String id;

    @Schema(description = "产品标识", example = "5282358452289536")
    private String productIdentification;

    @Schema(description = "设备标识", example = "7939700746264577")
    private String deviceIdentification;

    @Schema(description = "服务代码", example = "default_brightness")
    private String serviceCode;

    @Schema(description = "字段标识", example = "test1")
    private String field;

    @Schema(description = "值", example = "123")
    private Object value;

    @Schema(description = "描述", example = "test1")
    private String desc;

    @Schema(description = "数据类型", example = "string")
    private String dataType;

    @Schema(description = "是否多选", example = "false")
    private Boolean multiSelect;
}
