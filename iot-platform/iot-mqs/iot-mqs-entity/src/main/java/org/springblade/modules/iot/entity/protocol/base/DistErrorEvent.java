package org.springblade.modules.iot.entity.protocol.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * ============================================================================
 * Description:
 * 消息分发错误事件
 * ============================================================================
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "DistErrorEvent", description = "消息分发错误事件")
public class DistErrorEvent extends BaseEvent {
    @Schema(description = "请求唯一标识")
    private String reqId;

    @Schema(description = "错误代码")
    private Integer code;

    @Schema(description = "错误信息摘要")
    private String errorMessage;
}

