package org.springblade.common.cache.rule.plugin;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * <p>
 * 插件信息Key
 * </p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:插件唯一标识] -> obj
 * rule:def_plugin:pluginIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2024/8/28 4:45 下午
 */
public class PluginInfoCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(String pluginIdentification) {
        return new PluginInfoCacheKeyBuilder().key(pluginIdentification);
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Rule.DEF_PLUGIN_INFO;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.RULE;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
