package org.springblade.modules.iot.protocol.common.protocol;

/**
 * 协议类型枚举
 *
 * @author blade-iot
 */
public enum ProtocolType {

    MQTT("MQTT", "MQTT 协议"),
    MQTT_TLS("MQTTS", "MQTT over TLS"),
    HTTP("HTTP", "HTTP 协议"),
    HTTPS("HTTPS", "HTTPS 协议"),
    WEBSOCKET("WS", "WebSocket 协议"),
    WEBSOCKET_TLS("WSS", "WebSocket over TLS"),
    TCP("TCP", "TCP 透传"),
    MODBUS("Modbus", "Modbus 协议"),
    OPC_UA("OPC-UA", "OPC-UA 协议"),
    COAP("CoAP", "CoAP 协议"),
    GB28181("GB28181", "国标视频协议"),
    ISUP("ISUP", "海康 ISUP 协议");

    private final String code;
    private final String description;

    ProtocolType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProtocolType fromCode(String code) {
        for (ProtocolType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
