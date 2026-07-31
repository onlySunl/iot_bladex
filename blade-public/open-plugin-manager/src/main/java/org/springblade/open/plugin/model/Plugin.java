package org.springblade.open.plugin.model;

import lombok.Data;

/**
 * 插件信息
 *
 * @author Chill
 */
@Data
public class Plugin {

    /**
     * 插件ID
     */
    private String id;

    /**
     * 插件名称
     */
    private String name;

    /**
     * 插件版本
     */
    private String version;

    /**
     * 插件描述
     */
    private String description;

    /**
     * 插件作者
     */
    private String author;

    /**
     * 插件状态
     */
    private PluginStatus status = PluginStatus.INACTIVE;

    /**
     * 插件路径
     */
    private String path;

    /**
     * 插件类加载器
     */
    private transient ClassLoader classLoader;

}
