package org.springblade.modules.iot.entity.protocol.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ============================================================================
 * Description:
 * 订阅确认事件
 * ============================================================================
 *
 * @author mqttsnet
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "SubAckedEvent", description = "订阅确认事件")
public class SubAckedEvent extends BaseEvent {
    @Schema(description = "消息报文ID")
    private Long messageId;

    @Schema(description = "被订阅的Topic", example = "/device/+/status")
    private String topic;

    @Schema(description = "客户端地址（IP:PORT）")
    private String address;
}