package org.springblade.modules.iot.protocol.vo.result;

import java.io.Serial;
import java.util.List;

import lombok.experimental.Accessors;
import org.springblade.modules.iot.cache.vo.device.DeviceAclRuleCacheVO;
import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @program: iot-platform
 * @description: 设备认证结果VO
 * @packagename: org.springblade.modules.iot.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-07-13 23:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Accessors(chain = true)
@Schema(title = "DeviceAuthenticationResultVO", description = "设备认证结果")
public class DeviceAuthenticationResultVO<T> extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "认证结果")
    private Boolean certificationResult;

    @Schema(description = "认证失败原因")
    private String errorMessage;

    @Schema(description = "设备档案信息")
    private DeviceInfoResultVO deviceInfoResult;

    @Schema(description = "ACL授权规则信息")
    private List<DeviceAclRuleCacheVO> aclRuleListResult;


}
