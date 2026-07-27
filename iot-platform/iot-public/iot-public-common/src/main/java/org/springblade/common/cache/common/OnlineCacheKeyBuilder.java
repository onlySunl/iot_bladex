package org.springblade.common.cache.common;


import cn.hutool.core.util.StrUtil;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyTable;

/**
 * online:{userid} -> token (String)
 * <p>
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class OnlineCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTable() {
        return CacheKeyTable.ONLINE;
    }

    /**
     * 获取通配符
     *
     * @return key 前缀
     */
    @Override
    public String getPattern() {
        return StrUtil.format("{}:{}:*", getTenant(), getTable());
    }
}
