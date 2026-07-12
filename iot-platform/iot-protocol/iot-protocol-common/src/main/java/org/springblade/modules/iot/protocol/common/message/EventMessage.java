package org.springblade.modules.iot.protocol.common.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;

/**
 * 事件消息 - 设备上报事件
 *
 * @author blade-iot
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EventMessage extends DeviceMessage {

    /** 事件标识 */
    private String eventKey;

    /** 事件名称 */
    private String eventName;

    /** 事件类型: info / alert / error */
    private String eventType;

    /** 事件数据 */
    private Map<String, Object> data;

    public EventMessage() {
        setMessageType(MessageType.EVENT);
    }
}
