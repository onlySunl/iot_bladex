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
 * 客户端主动断开事件
 * ============================================================================
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "ByClientDisconnectEvent", description = "客户端主动断开事件")
public class ByClientDisconnectEvent extends BaseEvent {
    @Schema(description = "客户端地址（IP:PORT）")
    private String address;
}
