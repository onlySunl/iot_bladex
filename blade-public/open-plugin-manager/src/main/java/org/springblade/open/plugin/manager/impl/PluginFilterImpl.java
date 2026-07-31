package org.springblade.open.plugin.manager.impl;

import org.springblade.open.exp.client.PluginFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * SPI 实现。
 */
public class PluginFilterImpl implements PluginFilter {

    // inject db instance

    @Override
    public <T> List<FModel<T>> filter(List<FModel<T>> list) {
//        Long TenantId = ContextUtil.getTenantId();
        //    TenantId --》 list<pluginIDString>
        // from db
        List<String> pluginIds = new ArrayList<>();

        List<FModel<T>> result = new ArrayList<>();
        for (FModel<T> model : list) {
            model.getPluginId();
            if (pluginIds.contains(model.getPluginId())) {
                result.add(model);
            }
        }
        if (result.size() > 0) {
            // sort for version
        }
        return result;
    }
}
