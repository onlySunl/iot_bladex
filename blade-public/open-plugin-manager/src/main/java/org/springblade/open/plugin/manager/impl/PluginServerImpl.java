package org.springblade.open.plugin.manager.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springblade.open.exp.client.Constant;
import org.springblade.open.exp.client.ExpAppContext;
import org.springblade.open.exp.client.ExpAppContextSpiFactory;
import org.springblade.open.exp.client.Plugin;
import org.springblade.open.plugin.manager.PluginServer;
import org.springblade.open.plugin.manager.utils.ExpPluginsFileUtils;
import org.springframework.stereotype.Service;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginServerImpl
 * -----------------------------------------------------------------------------
 * Description:
 * Plugin Server Implementation
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/26       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/26 09:58
 */
@Slf4j
@Service
public class PluginServerImpl implements PluginServer {

    ExpAppContext expAppContext = ExpAppContextSpiFactory.getFirst();

    @Override
    public Plugin preload(String ip, String path) throws IOException {
        File file = null;
        boolean isTempFile = false;

        try {
            log.info("Starting to preload plugin from ip:{}, path: {}", ip, path);

            if (ExpPluginsFileUtils.isValidURL(path)) {
                log.info("Path is a valid URL, downloading file...");
                file = ExpPluginsFileUtils.downloadFileFromURL(path);
                isTempFile = true;
            } else {
                file = new File(path);
                if (!file.exists()) {
                    log.error("File does not exist at path: {}", path);
                    throw new IOException("File does not exist: " + path);
                }
            }

            Plugin plugin = expAppContext.preLoad(file);

            // appName --》 pluginId

            log.info("Successfully preloaded plugin from path: {}", path);
            return plugin;

        } catch (IOException e) {
            log.error("Failed to preload plugin from path: {}", path, e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred during preload from path: {}", path, e);
            throw new RuntimeException("An unexpected error occurred during plugin preload.", e);
        } finally {
            if (isTempFile) {
                log.info("Deleting temporary file: {}", file.getAbsolutePath());
                try {
                    ExpPluginsFileUtils.deleteTempFile(file);
                } catch (IOException e) {
                    log.warn("Failed to delete temporary file: {}", file.getAbsolutePath(), e);
                }
            }
        }
    }

    @Override
    public String install(String ip, String path) throws IOException {
        File file = null;
        // 标记文件是否为临时下载的文件
        boolean isTempFile = false;
        // 标记安装是否成功
        boolean installationSuccess = false;

        try {
            log.info("Starting installation of plugin from path: {} for ip: {}", path, ip);

            // 下载文件或加载本地文件
            if (ExpPluginsFileUtils.isValidURL(path)) {
                log.info("Path is a valid URL, downloading file...");
                file = ExpPluginsFileUtils.downloadFileFromURL(path);
                isTempFile = true;  // 下载的文件标记为临时文件
            } else {
                file = new File(path);
                if (!file.exists()) {
                    log.error("File does not exist at path: {}", path);
                    throw new IOException("File does not exist: " + path);
                }
            }

            // 加载插件
            Plugin plugin = expAppContext.load(file);
            log.info("Successfully installed plugin with ID: {} for ip: {}", plugin.getPluginId(), ip);

            // 使用插件的pluginId来重命名文件
            String newFileName = plugin.getPluginId() + ".jar";
            File renamedFile = new File(file.getParent(), newFileName);
            if (file.renameTo(renamedFile)) {
                log.info("Successfully renamed file to: {}", renamedFile.getAbsolutePath());
                // 将文件指向新的文件
                file = renamedFile;
            } else {
                log.warn("Failed to rename the file to pluginId: {}", newFileName);
            }

            // 如果安装成功，标记为成功
            installationSuccess = true;
            return plugin.getPluginId();

        } catch (IOException e) {
            log.error("Failed to install plugin from path: {} for ip: {}", path, ip, e);
            throw e;  // 重新抛出IOException以便外部处理
        } catch (Exception e) {
            log.error("Unexpected error occurred during installation from path: {} for ip: {}", path, ip, e);
            throw new RuntimeException("An unexpected error occurred during plugin installation.", e);
        } catch (Throwable e) {
            log.error("Unexpected error during installation process", e);
            throw new RuntimeException(e);
        } finally {
            // 在finally中执行删除临时文件的操作
            if (isTempFile && !installationSuccess) {
                // 如果是临时文件且安装失败，则删除临时文件
                if (file != null && file.exists()) {
                    try {
                        log.info("Deleting temporary file: {}", file.getAbsolutePath());
                        ExpPluginsFileUtils.deleteTempFile(file);
                    } catch (IOException e) {
                        log.warn("Failed to delete temporary file: {}", file.getAbsolutePath(), e);
                    }
                }
            }
        }
    }


    @Override
    public Boolean unInstall(String pluginId, String tenantId) throws Exception {
        log.info("Starting uninstallation of plugin with pluginId: {}, tenantId: {}", pluginId, tenantId);

        File pluginFile = null;

        try {
            // 获取插件文件路径，根据pluginId来查找对应的文件
            String pluginFilePath = System.getProperty(Constant.PLUGINS_PATH_KEY, "exp-plugins") + File.separator + pluginId + ".jar";
            pluginFile = new File(pluginFilePath);

            // 执行卸载
            expAppContext.unload(pluginId);
            log.info("Successfully uninstalled plugin with pluginId: {}, tenantId: {}", pluginId, tenantId);

            return true;

        } catch (Exception e) {
            log.error("Failed to uninstall plugin with pluginId: {}, tenantId: {}", pluginId, tenantId, e);
            return false;
        } finally {
            // 卸载成功后删除插件文件
            if (pluginFile != null && pluginFile.exists()) {
                try {
                    log.info("Deleting plugin file: {}", pluginFile.getAbsolutePath());
                    ExpPluginsFileUtils.deleteTempFile(pluginFile);
                } catch (IOException e) {
                    log.warn("Failed to delete plugin file: {}", pluginFile.getAbsolutePath(), e);
                }
            }
        }
    }


    @Override
    public List<String> getRuntimeAllPluginId() {
        log.info("Fetching all plugin IDs");
        // return db data.
        return expAppContext.getAllPluginId();
    }

    @Override
    public List<String> getDbAllPluginId() {
        // return db data.
        return null;
    }

    @Override
    public List<Plugin> getPluginsForTenant(String tenantId) throws Throwable {
        try {
            List<Plugin> allPlugins = loadAllPlugins();
            List<Plugin> tenantPlugins = new ArrayList<>();

            for (Plugin plugin : allPlugins) {
                String pluginId = plugin.getPluginId();
                if (isPluginAccessibleByTenant(pluginId, tenantId)) {
                    tenantPlugins.add(plugin);
                }
            }
            return tenantPlugins;
        } catch (Exception e) {
            log.error("Failed to get plugins for tenant: {}", tenantId, e);
            throw e;
        }
    }

    @Override
    public Optional<Plugin> getPluginForTenantById(String tenantId, String pluginId) {
        try {
            if (isPluginAccessibleByTenant(pluginId, tenantId)) {
                List<Plugin> plugins = loadAllPlugins();
                return plugins.stream().filter(i -> i.getPluginId().equals(pluginId)).findFirst();
            }
            return Optional.empty();
        } catch (Throwable e) {
            log.error("Failed to get plugin by ID: {} for tenant: {}", pluginId, tenantId, e);
            return Optional.empty();
        }
    }

    private List<Plugin> loadAllPlugins() throws Throwable {
        List<Plugin> allPlugins = new ArrayList<>();
        try {
            List<String> allPluginIds = expAppContext.getAllPluginId();

            for (String pluginId : allPluginIds) {
                // 根据 pluginId 加载插件实例，并封装为 FModel
                Plugin plugin = expAppContext.load(new File(pluginId));
                allPlugins.add(plugin);
            }
        } catch (Throwable t) {
            log.error("Failed to load all plugins", t);
            throw t;
        }

        return allPlugins;
    }

    private boolean isPluginAccessibleByTenant(String pluginId, String tenantId) {
        // 判断是否为 system 插件或租户特定的插件
        //String assignedTenantId = pluginIdTenantIdMap.get(pluginId);
        //return "system".equals(assignedTenantId) || tenantId.equals(assignedTenantId);
        return false;
    }

    @Override
    public String heartbeat(String ip, String port, String applicationName) {
        // 自定义实现，比如记录到数据库，或者发送到其他服务
        log.info("Custom heartbeat received from IP: {}, Port: {}, Application Name: {}", ip, port, applicationName);
        return "Heartbeat acknowledged";
    }


}
