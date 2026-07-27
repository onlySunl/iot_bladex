package org.springblade.common.cache.common;


import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyTable;

/**
 * 参数 KEY
 * {tenant}:PARAMETER_KEY:{key} -> value
 * <p>
 * #c_parameter
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class ParameterKeyCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTable() {
        return CacheKeyTable.PARAMETER_KEY;
    }

}
