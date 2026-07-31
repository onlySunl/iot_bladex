package org.springblade.open.plugin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 插件管理器配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.open.plugin")
public class PluginManagerProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 插件目录
     */
    private String pluginDir = "plugins";

    /**
     * 是否自动加载
     */
    private Boolean autoLoad = true;

    /**
     * 扫描间隔（秒）
     */
    private Integer scanInterval = 60;

}
