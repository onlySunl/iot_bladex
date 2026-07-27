package org.springblade.modules.iot.dto.alarm.channel.dingtalk;

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
 * File Name: DingTalkMessageParamDTO
 * -----------------------------------------------------------------------------
 * Description:
 * 钉钉推送消息参数
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/2       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/2 19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DingTalkMessageParamDTO", description = "钉钉推送消息参数")
public class DingTalkMessageParamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "签名", example = "SEC88889c5caf76ff5bee2a38d8430c14afa3b9ebe7f53cf7a36c6bfc10eee2713a")
    private String secret;

    @Schema(description = "认证token", example = "9a19abfd8896e781ccd7f4f49b8af8cfb378cceff10798feaf1d6639202ef5c6")
    private String token;
}
