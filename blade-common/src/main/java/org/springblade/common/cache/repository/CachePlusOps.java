package org.springblade.common.cache.repository;

import org.springblade.common.cache.CacheHashKey;
import org.springblade.common.cache.CacheKey;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存操作接口
 */
public interface CachePlusOps {
    void set(CacheKey key, Object value);
    Object get(CacheKey key);
    void del(CacheKey key);
    void hSet(CacheHashKey key, Object value);
    Object hGet(CacheHashKey key);
    void hDel(CacheHashKey key);
    Map<Object, Object> hGetAll(CacheKey key);
    void sAdd(CacheKey key, Object... values);
    Set<Object> sMembers(CacheKey key);
    void lPush(CacheKey key, Object value);
    List<Object> lRange(CacheKey key, long start, long end);
}
