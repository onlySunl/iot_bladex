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
 * <p>
 * 服务端强制断开事件
 * ============================================================================
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "ByServerDisconnectEvent", description = "服务端强制断开事件")
public class ByServerDisconnectEvent extends BaseEvent {
    @Schema(description = "客户端地址（IP:PORT）")
    private String address;

    @Schema(description = "断开原因代码")
    private Integer disconnectCode;

    @Schema(description = "断开说明")
    private String reason;
}

