package org.springblade.modules.iot.vo.query;

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
 * File Name: RemoveSubscriptionRequestVO
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/10/17       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/10/17 17:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RemoveSubscriptionRequestVO", description = "Request to remove a subscription from an MQTT session")
public class RemoveSubscriptionRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户ID", required = true, example = "thinglinks")
    private String tenantId;

    @Schema(description = "用户ID", required = true, example = "user123")
    private String userId;

    @Schema(description = "客户端ID", required = true, example = "client123")
    private String clientId;

    @Schema(description = "主题过滤器", required = true, example = "example/topic")
    private String topicFilter;
}
