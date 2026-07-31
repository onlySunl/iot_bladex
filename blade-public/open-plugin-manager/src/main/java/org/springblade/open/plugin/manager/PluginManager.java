package org.springblade.open.plugin.manager;

import lombok.extern.slf4j.Slf4j;
import org.springblade.open.plugin.config.PluginManagerProperties;
import org.springblade.open.plugin.model.Plugin;
import org.springblade.open.plugin.model.PluginStatus;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 插件管理器
 *
 * @author Chill
 */
@Slf4j
@Component
public class PluginManager {

    private final PluginManagerProperties properties;
    private final Map<String, Plugin> plugins = new ConcurrentHashMap<>();

    public PluginManager(PluginManagerProperties properties) {
        this.properties = properties;
    }

    /**
     * 加载插件
     *
     * @param pluginFile 插件文件
     * @return 插件信息
     */
    public Plugin loadPlugin(File pluginFile) {
        try {
            log.info("加载插件: {}", pluginFile.getName());
            
            Plugin plugin = new Plugin();
            plugin.setId(pluginFile.getName().replace(".jar", ""));
            plugin.setName(plugin.getId());
            plugin.setPath(pluginFile.getAbsolutePath());
            plugin.setStatus(PluginStatus.INACTIVE);
            
            plugins.put(plugin.getId(), plugin);
            log.info("插件加载成功: {}", plugin.getId());
            return plugin;
        } catch (Exception e) {
            log.error("插件加载失败: {}", pluginFile.getName(), e);
            throw new RuntimeException("插件加载失败", e);
        }
    }

    /**
     * 卸载插件
     *
     * @param pluginId 插件ID
     */
    public void unloadPlugin(String pluginId) {
        Plugin plugin = plugins.get(pluginId);
        if (plugin != null) {
            log.info("卸载插件: {}", pluginId);
            plugins.remove(pluginId);
        }
    }

    /**
     * 激活插件
     *
     * @param pluginId 插件ID
     */
    public void activatePlugin(String pluginId) {
        Plugin plugin = plugins.get(pluginId);
        if (plugin != null) {
            log.info("激活插件: {}", pluginId);
            plugin.setStatus(PluginStatus.ACTIVE);
        }
    }

    /**
     * 停用插件
     *
     * @param pluginId 插件ID
     */
    public void deactivatePlugin(String pluginId) {
        Plugin plugin = plugins.get(pluginId);
        if (plugin != null) {
            log.info("停用插件: {}", pluginId);
            plugin.setStatus(PluginStatus.INACTIVE);
        }
    }

    /**
     * 获取插件
     *
     * @param pluginId 插件ID
     * @return 插件信息
     */
    public Plugin getPlugin(String pluginId) {
        return plugins.get(pluginId);
    }

    /**
     * 获取所有插件
     *
     * @return 插件列表
     */
    public Map<String, Plugin> getAllPlugins() {
        return plugins;
    }

    /**
     * 扫描插件目录
     */
    public void scanPlugins() {
        if (!properties.getAutoLoad()) {
            return;
        }

        File pluginDir = new File(properties.getPluginDir());
        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            log.warn("插件目录不存在: {}", properties.getPluginDir());
            return;
        }

        File[] files = pluginDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files != null) {
            for (File file : files) {
                if (!plugins.containsKey(file.getName().replace(".jar", ""))) {
                    loadPlugin(file);
                }
            }
        }
    }

}
