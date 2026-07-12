package org.springblade.modules.iot.protocol.mqtt;

import lombok.Data;
import java.util.Map;

/**
 * MQTT 连接配置
 *
 * @author blade-iot
 */
@Data
public class MqttConnectionConfig {

    /** Broker 地址 */
    private String brokerUrl;

    /** 客户端ID */
    private String clientId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 是否清除会话 */
    private boolean cleanSession = true;

    /** 连接超时(秒) */
    private int connectionTimeout = 30;

    /** 保活间隔(秒) */
    private int keepAliveInterval = 60;

    /** 自动重连 */
    private boolean automaticReconnect = true;

    /** QoS 级别 */
    private int defaultQos = 1;

    /** SSL 配置 */
    private boolean sslEnabled = false;
    private String caCertPath;
    private String clientCertPath;
    private String clientKeyPath;

    /** 扩展配置 */
    private Map<String, Object> extraConfig;
}
