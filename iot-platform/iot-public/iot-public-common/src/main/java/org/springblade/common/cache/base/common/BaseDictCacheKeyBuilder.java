package org.springblade.common.cache.base.common;


import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheHashKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: dict:{dict_key}
 * field1: {item_key1} --> item_name
 * field2: {item_key2} --> item_name
 *
 * <p>
 * #c_dictionary_item
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class BaseDictCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable dictKey) {
        return new BaseDictCacheKeyBuilder().hashKey(dictKey);
    }

    public static CacheHashKey builder(String dictKey, String field) {
        return new BaseDictCacheKeyBuilder().hashFieldKey(field, dictKey);
    }


    @Override
    public String getTable() {
        return CacheKeyTable.Base.BASE_DICT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.BASE;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }
}
