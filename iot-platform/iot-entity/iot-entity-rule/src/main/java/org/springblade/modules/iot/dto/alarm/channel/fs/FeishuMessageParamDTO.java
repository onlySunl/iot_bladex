package org.springblade.modules.iot.dto.alarm.channel.fs;

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
 * File Name: FeishuMessageParamDTO
 * -----------------------------------------------------------------------------
 * Description:
 * 飞书推送消息参数
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/4       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/4 19:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "FeishuMessageParamDTO", description = "飞书推送消息参数")
public class FeishuMessageParamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "应用密钥", example = "BjJ0Dk3YQ1ODaiMkVTlNzcmyk4zDWE2r")
    private String appSecret;

    @Schema(description = "应用ID", example = "cli_a6dadc432134d00c")
    private String appId;

    @Schema(description = "认证token", example = "028dfcae-6383-4fa1-b20b-9edc43149264")
    private String token;
}
