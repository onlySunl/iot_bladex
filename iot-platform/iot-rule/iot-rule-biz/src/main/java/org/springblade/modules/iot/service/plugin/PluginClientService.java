package org.springblade.modules.iot.service.plugin;

/**
 * 插件客户端服务接口
 * @author mqttsnet
 */
public interface PluginClientService {

    String getApplicationName();

    String getIp();

    String getPort();

    /**
     * 实例启动
     */
    void start();

    /**
     * 实例停止
     */
    void stop();

    /**
     * 启动插件
     *
     * @param pluginId
     * @return
     */
    String startPlugin(String pluginId);

    /**
     * 停止插件
     *
     * @param pluginId
     * @return
     */
    String stopPlugin(String pluginId);

}
