package org.springblade.core.protocol.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 服务调用消息
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceCallMessage extends DeviceMessage {

    /**
     * 服务标识
     */
    private String serviceId;

    /**
     * 服务参数
     */
    private Map<String, Object> params;

    /**
     * 超时时间（毫秒）
     */
    private Long timeout;

    /**
     * 调用结果
     */
    private Object result;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String errorMessage;

    public ServiceCallMessage() {
        setMessageType(MessageType.SERVICE);
    }
}
