package org.springblade.core.protocol.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 属性消息
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PropertyMessage extends DeviceMessage {

    /**
     * 属性数据
     */
    private Map<String, Object> properties;

    /**
     * 属性值
     */
    private Object value;

    /**
     * 属性标识
     */
    private String propertyKey;

    public PropertyMessage() {
        setMessageType(MessageType.PROPERTY);
    }
}
