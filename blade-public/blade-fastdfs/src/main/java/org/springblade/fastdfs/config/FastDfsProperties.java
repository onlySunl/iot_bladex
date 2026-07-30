package org.springblade.fastdfs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FastDFS 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.fastdfs")
public class FastDfsProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 连接超时时间（秒）
     */
    private Integer connectTimeout = 5;

    /**
     * 网络超时时间（秒）
     */
    private Integer networkTimeout = 30;

    /**
     * 字符集
     */
    private String charset = "UTF-8";

    /**
     * Tracker 服务器地址
     */
    private String trackerServers = "localhost:22122";

    /**
     * 连接池最大连接数
     */
    private Integer maxTotal = 50;

    /**
     * 每个 Tracker 的最大连接数
     */
    private Integer maxPerRoute = 20;

}
