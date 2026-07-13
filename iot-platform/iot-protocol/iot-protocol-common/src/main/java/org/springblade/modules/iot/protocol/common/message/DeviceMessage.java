package org.springblade.modules.iot.protocol.common.message;
import org.springblade.modules.iot.common.enums.MessageType;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

/**
 * 设备消息基类 - 所有设备上报/下发消息的抽象
 *
 * @author blade-iot
 */
@Data
public class DeviceMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private String messageId;

    /** 设备ID */
    private String deviceId;

    /** 产品ID */
    private String productId;

    /** 租户ID */
    private String tenantId;

    /** 时间戳 */
    private Long timestamp;

    /** 消息头 */
    private Map<String, Object> headers;

    /** 消息类型 */
    private MessageType messageType;

    public enum MessageType {
        /** 属性上报 */
        PROPERTY,
        /** 事件上报 */
        EVENT,
        /** 服务调用 */
        SERVICE_CALL,
        /** 服务响应 */
        SERVICE_RESPONSE,
        /** 设备上线 */
        ONLINE,
        /** 设备离线 */
        OFFLINE,
        /** 设备注册 */
        REGISTER
    }
}
