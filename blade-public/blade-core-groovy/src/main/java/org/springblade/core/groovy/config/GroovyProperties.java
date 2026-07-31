package org.springblade.core.groovy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Groovy 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.groovy")
public class GroovyProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 是否启用缓存
     */
    private Boolean cacheEnabled = true;

    /**
     * 脚本缓存大小
     */
    private Integer cacheSize = 100;

    /**
     * 脚本执行超时时间（毫秒）
     */
    private Long timeout = 5000L;

}
