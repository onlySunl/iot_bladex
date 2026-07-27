package org.springblade.common.cache.common;


import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyTable;

/**
 * 参数 KEY
 * {tenant}:TOTAL_PV -> long
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class TotalPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TotalPvCacheKeyBuilder().key();
    }

    @Override
    public String getTable() {
        return CacheKeyTable.TOTAL_PV;
    }
}
