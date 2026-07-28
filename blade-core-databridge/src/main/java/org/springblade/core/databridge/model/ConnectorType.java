package org.springblade.core.databridge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 连接器类型枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum ConnectorType {

    HTTP("HTTP", "HTTP 连接器"),
    MQTT("MQTT", "MQTT 连接器"),
    KAFKA("KAFKA", "Kafka 连接器"),
    TCP("TCP", "TCP 连接器"),
    UDP("UDP", "UDP 连接器"),
    WEBSOCKET("WEBSOCKET", "WebSocket 连接器");

    private final String code;
    private final String description;
}
