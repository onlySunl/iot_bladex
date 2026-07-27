package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: TopoOtaReadParam
 * -----------------------------------------------------------------------------
 * Description:
 * OTA 读取设备软固件版本信息
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/3/20       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/3/20 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "TopoOtaReadParam", description = "Request Data Structure for Reading Device Software and Firmware Version Information")
public class TopoOtaReadParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for the device", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceIdentification;

    @Schema(description = "Type of OTA package", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer packageType;

}
