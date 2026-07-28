package org.springblade.core.tds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TDS 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.tds")
public class TdsProperties {

    /**
     * 是否启用
     */
    private boolean enabled = false;

    /**
     * 驱动类名
     */
    private String driverClassName = "com.taosdata.jdbc.TSDBDriver";

    /**
     * 连接URL
     */
    private String url = "jdbc:TAOS://localhost:6030/iot_ts";

    /**
     * 用户名
     */
    private String username = "root";

    /**
     * 密码
     */
    private String password = "taosdata";
}
