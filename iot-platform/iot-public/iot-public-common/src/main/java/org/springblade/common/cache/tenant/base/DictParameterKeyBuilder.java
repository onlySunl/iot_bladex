package org.springblade.common.cache.tenant.base;


import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheHashKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: lc:def_parameter:tenant:id:string:{id}
 * value: obj
 *
 * <p>
 * #def_parameter
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DictParameterKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable id) {
        return new DictParameterKeyBuilder().hashKey(id);
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.DEF_PARAMETER;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
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
