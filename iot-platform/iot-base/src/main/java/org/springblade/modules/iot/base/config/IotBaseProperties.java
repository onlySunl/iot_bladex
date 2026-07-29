package org.springblade.modules.iot.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * IoT 基础配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "iot.base")
public class IotBaseProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 默认租户ID
     */
    private String defaultTenantId = "000000";

    /**
     * 设备Topic前缀
     */
    private String deviceTopicPrefix = "/sys";

    /**
     * 产品Topic前缀
     */
    private String productTopicPrefix = "/sys";

    /**
     * 广播Topic前缀
     */
    private String broadcastTopicPrefix = "/broadcast";

    /**
     * 设备离线超时时间（秒）
     */
    private Integer deviceOfflineTimeout = 300;

    /**
     * 设备心跳超时时间（秒）
     */
    private Integer deviceHeartbeatTimeout = 60;

}
