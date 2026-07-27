package org.springblade.common.utils.hlc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 混合逻辑时钟工具类
 */
public class HybridLogicalClockUtil {
    private static final AtomicLong COUNTER = new AtomicLong(0);
    
    public static long generateTimestamp() {
        return System.currentTimeMillis() * 10000 + COUNTER.incrementAndGet() % 10000;
    }
}
