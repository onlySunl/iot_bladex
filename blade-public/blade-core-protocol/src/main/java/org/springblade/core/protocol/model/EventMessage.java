package org.springblade.core.protocol.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 事件消息
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EventMessage extends DeviceMessage {

    /**
     * 事件标识
     */
    private String eventKey;

    /**
     * 事件数据
     */
    private Map<String, Object> data;

    /**
     * 事件类型
     */
    private String eventType;

    public EventMessage() {
        setMessageType(MessageType.EVENT);
    }
}
