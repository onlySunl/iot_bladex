package org.springblade.modules.iot.protocol.common.message;
import org.springblade.modules.iot.common.enums.MessageType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;

/**
 * 服务调用消息 - 平台向设备下发服务调用指令
 *
 * @author blade-iot
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceCallMessage extends DeviceMessage {

    /** 服务标识 */
    private String serviceKey;

    /** 调用参数 */
    private Map<String, Object> params;

    /** 超时时间(毫秒) */
    private Long timeout;

    public ServiceCallMessage() {
        setMessageType(MessageType.SERVICE_CALL);
    }
}
