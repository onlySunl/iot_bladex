package org.springblade.open.exp.client;

/**
 * @version 1.0
 * @author mqttsnet
 * @Description
 * @date 2023/8/12
 **/
@ExpSpi
public interface PluginConfig {

    static PluginConfig getSpi() {
        return SpiFactory.get(PluginConfig.class, new PluginConfigDefault());
    }

    String getProperty(String pluginId, String key, String defaultValue);

}
