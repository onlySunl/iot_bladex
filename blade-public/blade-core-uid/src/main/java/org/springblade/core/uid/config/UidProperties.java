package org.springblade.core.uid.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * UID 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.uid")
public class UidProperties {

    /**
     * 是否启用 UID 生成器
     */
    private boolean enabled = true;

    /**
     * 生成器类型: snowflake / uuid
     */
    private String type = "snowflake";

    /**
     * 工作机器ID (0-1023)
     */
    private long workerId = 1;

    /**
     * 是否使用随机工作机器ID
     */
    private boolean randomWorkerId = false;

    /**
     * 随机工作机器ID的最大值
     */
    private int maxWorkerId = 1023;
}
