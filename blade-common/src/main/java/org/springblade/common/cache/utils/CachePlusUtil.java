package org.springblade.common.cache.utils;

import org.springblade.common.cache.repository.CachePlusOps;

/**
 * 缓存工具类
 */
public class CachePlusUtil {
    private static CachePlusOps cachePlusOps;
    
    public static void setCachePlusOps(CachePlusOps ops) {
        cachePlusOps = ops;
    }
    
    public static CachePlusOps getCachePlusOps() {
        return cachePlusOps;
    }
}
