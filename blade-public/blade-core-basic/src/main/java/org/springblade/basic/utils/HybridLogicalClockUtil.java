package org.springblade.basic.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * HybridLogicalClockUtil 是一个混合逻辑时钟(HLC)工具类,产出严格单调递增的时钟值,
 * * 用作分布式事件的因果排序键(event-time LWW CAS 单调写),可跨异步消费 / 乱序 / 抖动重连保序。
 * * <p>编码:{@code (物理毫秒 << 16) | 16 位逻辑计数器},
 * * 严禁当时间戳写入 datetime / 时序索引。同一毫秒内多次调用靠低 16 位计数器保证严格递增。
 *
 * @author mqttsnet
 * @since 2026-06-03
 **/
public class HybridLogicalClockUtil {

    /**
     * 低位逻辑计数器位数。
     */
    private static final int LOGICAL_BITS = 16;

    /**
     * 当前 HLC 状态:高位物理毫秒,低位逻辑计数器。
     */
    private static final AtomicLong STATE = new AtomicLong(0L);

    private HybridLogicalClockUtil() {
    }

    /**
     * 取下一个严格单调递增的 HLC 值。
     *
     * <p>物理毫秒推进 → 高位刷新、计数器归零;否则(同毫秒 / 时钟回拨)→ 在上次值上 +1 续逻辑计数器。
     *
     * @return 严格单调递增的 HLC(高 48 位物理毫秒,低 16 位逻辑计数器)
     */
    public static long nextHlc() {
        while (true) {
            long last = STATE.get();
            long lastPhysical = last >>> LOGICAL_BITS;
            long nowPhysical = System.currentTimeMillis();
            long next = nowPhysical > lastPhysical
                ? (nowPhysical << LOGICAL_BITS)
                : (last + 1);
            if (STATE.compareAndSet(last, next)) {
                return next;
            }
        }
    }
}
