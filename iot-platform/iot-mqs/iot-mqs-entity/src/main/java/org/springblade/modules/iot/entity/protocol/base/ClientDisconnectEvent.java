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
 * 客户端断开事件
 * ============================================================================
 *
 * @author mqttsnet
 * @version 1.0.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "ClientDisconnectEvent", description = "客户端断开事件")
public class ClientDisconnectEvent extends BaseEvent {

    @Schema(description = "协议版本")
    private String version;

    @Schema(description = "客户端地址（IP:PORT）")
    private String address;

    @Schema(description = "通信通道ID")
    private String channelId;
}
