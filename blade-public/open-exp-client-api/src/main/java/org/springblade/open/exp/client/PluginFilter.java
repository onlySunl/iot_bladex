package org.springblade.open.exp.client;

import lombok.Data;

import java.util.List;

/**
 * @author mqttsnet
 *
 **/
@ExpSpi
public interface PluginFilter {

    <T> List<FModel<T>> filter(List<FModel<T>> list);

    @Data
    class FModel<T> {
        T t;
        String pluginId;

        public FModel(T t, String pluginId) {
            this.t = t;
            this.pluginId = pluginId;
        }
    }
}
