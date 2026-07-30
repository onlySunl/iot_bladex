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
 * @program: iot-platform
 * @description: 发送消息VO
 * @packagename: org.springblade.modules.iot.mqtt.vo
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 19:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "PublishMessageRequestVO", description = "发送消息VO")
public class PublishWebSocketMessageRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "可选的调用者提供的请求ID", example = "1234567890")
    private Long reqId;

    @Schema(description = "租户ID", required = true, example = "iot")
    private Long tenantId;

    @Schema(description = "客户端ID", required = true, example = "client123")
    private String clientId;

    @Schema(description = "消息主题", required = true, example = "exampleTopic")
    private String topic;

    @Schema(description = "发布者类型", required = true, example = "web")
    private String clientType;

    @Schema(description = "消息负载，将作为二进制处理", requiredMode = Schema.RequiredMode.REQUIRED)
    private String payload;
}
