package org.springblade.modules.iot.protocol.common.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;

/**
 * 属性消息 - 设备上报属性数据
 *
 * @author blade-iot
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PropertyMessage extends DeviceMessage {

    /** 属性数据 key=功能标识, value=属性值 */
    private Map<String, Object> properties;

    public PropertyMessage() {
        setMessageType(MessageType.PROPERTY);
    }
}
