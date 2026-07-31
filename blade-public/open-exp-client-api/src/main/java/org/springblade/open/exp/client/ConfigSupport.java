package org.springblade.open.exp.client;

import lombok.Data;

/**
 * @version 1.0
 * @author mqttsnet
 * @Description
 * @date 2023/8/17
 **/
@Data
public class ConfigSupport {

    private String keyName;

    private String defaultValue;

    private String desc;

    private boolean required;

    private String pluginId;

    public ConfigSupport(String keyName) {
        this.keyName = keyName;
    }

    public ConfigSupport(String keyName, String defaultValue) {
        this.keyName = keyName;
        this.defaultValue = defaultValue;
    }

    public String getProperty() {
        return PluginConfig.getSpi().getProperty(pluginId, keyName, defaultValue);
    }
}
