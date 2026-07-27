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
 * 取消订阅确认事件
 * ============================================================================
 *
 * @author mqttsnet
 * @version 1.0.0
 * -----------------------------------------------------------------------------
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "UnsubAckedEvent", description = "取消订阅确认事件")
public class UnsubAckedEvent extends BaseEvent {
    @Schema(description = "消息报文ID")
    private Long messageId;

    @Schema(description = "被取消订阅的Topic", example = "/device/+/status")
    private String topic;

    @Schema(description = "客户端地址（IP:PORT）")
    private String address;
}
