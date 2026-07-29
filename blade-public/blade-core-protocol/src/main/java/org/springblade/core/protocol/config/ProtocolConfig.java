package org.springblade.core.protocol.config;

import lombok.Data;

import java.util.Map;

/**
 * 协议配置
 *
 * @author Chill
 */
@Data
public class ProtocolConfig {

    /**
     * 协议类型
     */
    private String protocolType;

    /**
     * 连接地址
     */
    private String host;

    /**
     * 连接端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 主题
     */
    private String topic;

    /**
     * QoS 级别
     */
    private Integer qos = 1;

    /**
     * 是否启用 SSL
     */
    private Boolean sslEnabled = false;

    /**
     * 其他配置参数
     */
    private Map<String, Object> extraConfig;
}
