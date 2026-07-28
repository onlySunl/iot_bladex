package org.springblade.common.iot.cache.tenant.base;


import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: lc:def_dict:tenant:id:string:{dict_key}
 * field1: {item_key1} --> item_name
 * field2: {item_key2} --> item_name
 *
 * <p>
 * #def_dict
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DictCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(Serializable dictKey) {
        return new DictCacheKeyBuilder().hashKey(dictKey);
    }

    public static CacheHashKey builder(String dictKey, String itemKey) {
        return new DictCacheKeyBuilder().hashFieldKey(itemKey, dictKey);
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.DICT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }
}
