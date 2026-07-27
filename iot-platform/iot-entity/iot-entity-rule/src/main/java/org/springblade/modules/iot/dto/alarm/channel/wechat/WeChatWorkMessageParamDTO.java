package org.springblade.modules.iot.dto.alarm.channel.wechat;

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
 * File Name: WeChatWorkMessageParamDTO
 * -----------------------------------------------------------------------------
 * Description:企业微信推送消息参数
 * <p>
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
 * @date 2024/6/4 19:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "WeChatWorkMessageParamDTO", description = "企业微信推送消息参数")
public class WeChatWorkMessageParamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "认证token", example = "dae07998-7385-4640-8e3f-436752c279c3")
    private String token;
}
