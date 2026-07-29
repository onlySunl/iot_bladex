package org.springblade.core.dynamic.tp.executor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.dynamic.tp.monitor.DynamicTpMonitor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 动态可调整线程池执行器
 *
 * @author Chill
 */
@Slf4j
public class DynamicThreadPoolExecutor extends ThreadPoolExecutor {

    /**
     * 线程池名称
     */
    @Getter
    private final String poolName;

    /**
     * 拒绝任务计数
     */
    private final AtomicLong rejectCount = new AtomicLong(0);

    /**
     * 监控器
     */
    private DynamicTpMonitor monitor;

    public DynamicThreadPoolExecutor(String poolName,
                                     int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.poolName = poolName;
    }

    public DynamicThreadPoolExecutor(String poolName,
                                     int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.poolName = poolName;
    }

    public DynamicThreadPoolExecutor(String poolName,
                                     int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.poolName = poolName;
    }

    /**
     * 设置监控器
     */
    public void setMonitor(DynamicTpMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * 动态调整核心线程数
     */
    public void setCorePoolSize(int corePoolSize) {
        int oldCorePoolSize = getCorePoolSize();
        super.setCorePoolSize(corePoolSize);
        log.info("[{}] 核心线程数调整: {} -> {}", poolName, oldCorePoolSize, corePoolSize);
    }

    /**
     * 动态调整最大线程数
     */
    public void setMaximumPoolSize(int maximumPoolSize) {
        int oldMaximumPoolSize = getMaximumPoolSize();
        super.setMaximumPoolSize(maximumPoolSize);
        log.info("[{}] 最大线程数调整: {} -> {}", poolName, oldMaximumPoolSize, maximumPoolSize);
    }

    /**
     * 动态调整空闲线程存活时间
     */
    public void setKeepAliveTime(long keepAliveTime, TimeUnit unit) {
        long oldKeepAliveTime = getKeepAliveTime(unit);
        super.setKeepAliveTime(keepAliveTime, unit);
        log.info("[{}] 空闲线程存活时间调整: {} -> {} {}", poolName, oldKeepAliveTime, keepAliveTime, unit);
    }

    /**
     * 获取拒绝任务数
     */
    public long getRejectCount() {
        return rejectCount.get();
    }

    /**
     * 重置拒绝任务计数
     */
    public void resetRejectCount() {
        rejectCount.set(0);
    }

    /**
     * 获取队列使用率
     */
    public double getQueueUsage() {
        BlockingQueue<Runnable> queue = getQueue();
        if (queue.isEmpty()) {
            return 0.0;
        }
        return (double) queue.size() / queue.size();
    }

    /**
     * 获取活跃线程率
     */
    public double getActiveThreadUsage() {
        int maxPoolSize = getMaximumPoolSize();
        if (maxPoolSize == 0) {
            return 0.0;
        }
        return (double) getActiveCount() / maxPoolSize;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        if (monitor != null) {
            monitor.recordTaskStart(poolName);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (monitor != null) {
            monitor.recordTaskEnd(poolName, t);
        }
    }

    @Override
    public void rejectedExecution(Runnable r, RejectedExecutionHandler handler) {
        rejectCount.incrementAndGet();
        log.warn("[{}] 任务被拒绝, 当前活跃线程: {}, 队列大小: {}", 
                poolName, getActiveCount(), getQueue().size());
        super.rejectedExecution(r, handler);
    }

    /**
     * 获取线程池状态快照
     */
    public ThreadPoolSnapshot getSnapshot() {
        return new ThreadPoolSnapshot(
                poolName,
                getCorePoolSize(),
                getMaximumPoolSize(),
                getActiveCount(),
                getPoolSize(),
                getLargestPoolSize(),
                getQueue().size(),
                getQueue().remainingCapacity(),
                getCompletedTaskCount(),
                getTaskCount(),
                rejectCount.get()
        );
    }

    /**
     * 线程池状态快照
     */
    @Getter
    public static class ThreadPoolSnapshot {
        private final String poolName;
        private final int corePoolSize;
        private final int maximumPoolSize;
        private final int activeCount;
        private final int poolSize;
        private final int largestPoolSize;
        private final int queueSize;
        private final int queueRemainingCapacity;
        private final long completedTaskCount;
        private final long taskCount;
        private final long rejectCount;

        public ThreadPoolSnapshot(String poolName, int corePoolSize, int maximumPoolSize,
                                  int activeCount, int poolSize, int largestPoolSize,
                                  int queueSize, int queueRemainingCapacity,
                                  long completedTaskCount, long taskCount, long rejectCount) {
            this.poolName = poolName;
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.activeCount = activeCount;
            this.poolSize = poolSize;
            this.largestPoolSize = largestPoolSize;
            this.queueSize = queueSize;
            this.queueRemainingCapacity = queueRemainingCapacity;
            this.completedTaskCount = completedTaskCount;
            this.taskCount = taskCount;
            this.rejectCount = rejectCount;
        }

        @Override
        public String toString() {
            return String.format("ThreadPool[%s] core=%d, max=%d, active=%d, pool=%d, largest=%d, " +
                            "queue=%d/%d, completed=%d, total=%d, rejected=%d",
                    poolName, corePoolSize, maximumPoolSize, activeCount, poolSize, largestPoolSize,
                    queueSize, queueSize + queueRemainingCapacity, completedTaskCount, taskCount, rejectCount);
        }
    }
}
