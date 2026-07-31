package org.springblade.open.plugin.model;

import lombok.Data;

/**
 * 插件描述符
 *
 * @author Chill
 */
@Data
public class PluginDescriptor {

    /**
     * 插件ID
     */
    private String pluginId;

    /**
     * 插件名称
     */
    private String pluginName;

    /**
     * 插件版本
     */
    private String pluginVersion;

    /**
     * 插件描述
     */
    private String pluginDescription;

    /**
     * 插件作者
     */
    private String pluginAuthor;

    /**
     * 主类
     */
    private String mainClass;

    /**
     * 依赖插件
     */
    private String[] dependencies;

}
