package org.springblade.open.plugin.manager;

import org.springblade.open.exp.client.Plugin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * PluginServer 接口定义了一组用于管理插件的服务 API。
 * 提供插件的预加载、安装、卸载以及获取插件信息的功能。
 *
 * @author xiaonannet
 */
public interface PluginServer {
    /**
     * 心跳
     *
     * @param ip              ip
     * @param port            port
     * @param applicationName applicationName
     * @return success or fail
     */
    String heartbeat(String ip, String port, String applicationName);

    /**
     * 预加载插件的方法，支持本地文件路径和 HTTP/HTTPS URL。
     * <p>
     * 该方法只读取插件的元信息，加载启动类和配置文件，不进行 bean 加载。
     *
     * @param ip   插件运行所属实例IP
     * @param path 插件文件的路径，可以是本地路径或远程 URL
     * @return {@link Plugin} 预加载的插件对象
     * @throws IOException 如果文件操作或下载过程出错
     */
    Plugin preload(String ip, String path) throws IOException;

    /**
     * 安装插件的方法，支持本地文件路径和 HTTP/HTTPS URL。
     * <p>
     * 该方法将插件加载到系统中，并记录插件 ID 与租户 ID 的映射关系，同时生成一个随机的排序值。
     * 如果路径是一个 URL，则会先下载文件到本地临时目录，再进行加载。
     *
     * @param ip   插件运行所属实例IP
     * @param path 插件文件的路径，可以是本地路径或远程 URL
     * @return {@link String} 已安装插件的插件 ID
     * @throws IOException 如果文件操作或下载过程出错
     */
    String install(String ip, String path) throws IOException;

    /**
     * 卸载插件的方法。
     * <p>
     * 该方法将卸载指定的插件 ID，并清理相关的租户映射和排序信息。
     * 卸载完成后，将返回一个表示操作成功或失败的布尔值。
     *
     * @param pluginId 要卸载的插件 ID
     * @return {@link Boolean} True: 卸载成功，False: 卸载失败
     * @throws Exception 如果卸载过程中出错
     */
    Boolean unInstall(String pluginId, String tenantId) throws Exception;

    /**
     * 获取当前所有的插件 ID。
     * <p>
     * 该方法返回系统中所有已加载插件的 ID 列表。
     *
     * @return {@link List<String>} 所有插件 ID 的列表
     */
    List<String> getRuntimeAllPluginId();

    List<String> getDbAllPluginId();

    /**
     * 获取指定租户下的所有可用插件。
     *
     * @param tenantId 租户ID
     * @return {@link List<PluginFilter.FModel>} 租户下的所有插件实例
     */
    List<Plugin> getPluginsForTenant(String tenantId) throws Throwable;

    /**
     * 获取指定租户下某个插件ID的插件实例。
     *
     * @param tenantId 租户ID
     * @param pluginId 插件ID
     * @return {@link Optional<PluginFilter.FModel>} 对应插件实例的 Optional 包装
     */
    Optional<Plugin> getPluginForTenantById(String tenantId, String pluginId);


}
