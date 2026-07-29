package org.springblade.core.dynamic.tp.monitor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.dynamic.tp.executor.DynamicThreadPoolExecutor;
import org.springblade.core.dynamic.tp.properties.DynamicTpProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 动态线程池监控器
 *
 * @author Chill
 */
@Slf4j
public class DynamicTpMonitor {

    private final DynamicTpProperties properties;
    private final MeterRegistry meterRegistry;
    private final Map<String, AtomicLong> taskStartCountMap = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> taskEndCountMap = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> taskExceptionCountMap = new ConcurrentHashMap<>();

    public DynamicTpMonitor(DynamicTpProperties properties, MeterRegistry meterRegistry) {
        this.properties = properties;
        this.meterRegistry = meterRegistry;
    }

    /**
     * 注册线程池到监控
     */
    public void registerExecutor(DynamicThreadPoolExecutor executor) {
        String poolName = executor.getPoolName();
        taskStartCountMap.putIfAbsent(poolName, new AtomicLong(0));
        taskEndCountMap.putIfAbsent(poolName, new AtomicLong(0));
        taskExceptionCountMap.putIfAbsent(poolName, new AtomicLong(0));

        executor.setMonitor(this);

        if (properties.getMonitor().isMicrometerEnabled() && meterRegistry != null) {
            registerMicrometerMetrics(executor);
        }

        log.info("注册线程池监控: {}", poolName);
    }

    /**
     * 注册 Micrometer 指标
     */
    private void registerMicrometerMetrics(DynamicThreadPoolExecutor executor) {
        String poolName = executor.getPoolName();
        Tags tags = Tags.of("pool", poolName);

        // 核心线程数
        meterRegistry.gauge("dynamic.tp.core.size", tags, executor, DynamicThreadPoolExecutor::getCorePoolSize);
        // 最大线程数
        meterRegistry.gauge("dynamic.tp.max.size", tags, executor, DynamicThreadPoolExecutor::getMaximumPoolSize);
        // 活跃线程数
        meterRegistry.gauge("dynamic.tp.active.count", tags, executor, DynamicThreadPoolExecutor::getActiveCount);
        // 当前线程数
        meterRegistry.gauge("dynamic.tp.pool.size", tags, executor, DynamicThreadPoolExecutor::getPoolSize);
        // 历史最大线程数
        meterRegistry.gauge("dynamic.tp.largest.size", tags, executor, DynamicThreadPoolExecutor::getLargestPoolSize);
        // 队列大小
        meterRegistry.gauge("dynamic.tp.queue.size", tags, executor, e -> e.getQueue().size());
        // 队列剩余容量
        meterRegistry.gauge("dynamic.tp.queue.remaining", tags, executor, e -> e.getQueue().remainingCapacity());
        // 已完成任务数
        meterRegistry.gauge("dynamic.tp.task.completed", tags, executor, DynamicThreadPoolExecutor::getCompletedTaskCount);
        // 总任务数
        meterRegistry.gauge("dynamic.tp.task.total", tags, executor, DynamicThreadPoolExecutor::getTaskCount);
        // 拒绝任务数
        meterRegistry.gauge("dynamic.tp.task.rejected", tags, executor, DynamicThreadPoolExecutor::getRejectCount);
    }

    /**
     * 记录任务开始
     */
    public void recordTaskStart(String poolName) {
        AtomicLong counter = taskStartCountMap.get(poolName);
        if (counter != null) {
            counter.incrementAndGet();
        }
    }

    /**
     * 记录任务结束
     */
    public void recordTaskEnd(String poolName, Throwable t) {
        AtomicLong counter = taskEndCountMap.get(poolName);
        if (counter != null) {
            counter.incrementAndGet();
        }
        if (t != null) {
            AtomicLong exceptionCounter = taskExceptionCountMap.get(poolName);
            if (exceptionCounter != null) {
                exceptionCounter.incrementAndGet();
            }
        }
    }

    /**
     * 获取任务开始计数
     */
    public long getTaskStartCount(String poolName) {
        AtomicLong counter = taskStartCountMap.get(poolName);
        return counter != null ? counter.get() : 0;
    }

    /**
     * 获取任务结束计数
     */
    public long getTaskEndCount(String poolName) {
        AtomicLong counter = taskEndCountMap.get(poolName);
        return counter != null ? counter.get() : 0;
    }

    /**
     * 获取任务异常计数
     */
    public long getTaskExceptionCount(String poolName) {
        AtomicLong counter = taskExceptionCountMap.get(poolName);
        return counter != null ? counter.get() : 0;
    }

    /**
     * 打印线程池状态
     */
    public void printPoolStats(DynamicThreadPoolExecutor executor) {
        if (properties.getMonitor().isLogEnabled()) {
            log.info("线程池状态: {}", executor.getSnapshot());
        }
    }
}
