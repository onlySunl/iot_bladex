package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * -----------------------------------------------------------------------------
 * File Name: PublishMessageRequestParam
 * -----------------------------------------------------------------------------
 * Description:
 * 发布消息
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/4/17       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/4/17 18:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(title = "PublishWebSocketMessageRequestParam", description = "Parameters required for publishing a message via WebSocket.")
public class PublishWebSocketMessageRequestParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "MQTT topic to which the message will be published.")
    @NotEmpty(message = "MQTT topic cannot be empty")
    private String topic;

    @Schema(description = "clientId.")
    @NotEmpty(message = "clientId cannot be empty")
    private String clientId;

    @Schema(description = "The actual message payload to be sent.")
    @NotNull(message = "Message payload cannot be null")
    private String payload;

    @Schema(description = "Tenant ID associated with the message, for multi-tenant environments.")
    @NotEmpty(message = "Tenant ID cannot be empty")
    private String tenantId;

    @Schema(description = "Expiry seconds for the message.")
    private String expirySeconds;

    @Schema(description = "Additional metadata associated with the message.")
    private Map<String, String> metadata;
}
