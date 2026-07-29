package org.springblade.core.protocol.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 设备消息基类
 *
 * @author Chill
 */
@Data
public class DeviceMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 消息时间戳
     */
    private LocalDateTime timestamp;

    /**
     * 消息头
     */
    private Map<String, Object> headers;

    /**
     * 消息类型
     */
    private MessageType messageType;

    /**
     * 消息类型枚举
     */
    public enum MessageType {
        /**
         * 属性上报
         */
        PROPERTY,
        /**
         * 事件上报
         */
        EVENT,
        /**
         * 服务调用
         */
        SERVICE,
        /**
         * 设备上线
         */
        ONLINE,
        /**
         * 设备下线
         */
        OFFLINE
    }
}
