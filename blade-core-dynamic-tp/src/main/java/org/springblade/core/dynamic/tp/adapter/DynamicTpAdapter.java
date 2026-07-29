package org.springblade.core.dynamic.tp.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.dynamic.tp.executor.DynamicThreadPoolExecutor;
import org.springblade.core.dynamic.tp.monitor.DynamicTpMonitor;
import org.springblade.core.dynamic.tp.properties.DynamicTpProperties;
import org.springblade.core.dynamic.tp.properties.DynamicTpProperties.TpExecutorProperties;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 动态线程池适配器
 *
 * @author Chill
 */
@Slf4j
public class DynamicTpAdapter {

    private final DynamicTpProperties properties;
    private final DynamicTpMonitor monitor;
    private final Map<String, DynamicThreadPoolExecutor> executorMap;

    public DynamicTpAdapter(DynamicTpProperties properties,
                            DynamicTpMonitor monitor,
                            Map<String, DynamicThreadPoolExecutor> executorMap) {
        this.properties = properties;
        this.monitor = monitor;
        this.executorMap = executorMap;
        initExecutors();
    }

    /**
     * 初始化线程池
     */
    private void initExecutors() {
        for (TpExecutorProperties executorProps : properties.getExecutors()) {
            createExecutor(executorProps);
        }
    }

    /**
     * 创建线程池
     */
    private void createExecutor(TpExecutorProperties props) {
        String poolName = props.getName();
        
        // 创建队列
        BlockingQueue<Runnable> queue = createQueue(props);
        
        // 创建线程工厂
        ThreadFactory threadFactory = new DynamicThreadFactory(props.getThreadNamePrefix());
        
        // 创建拒绝策略
        RejectedExecutionHandler handler = createRejectedHandler(props.getRejectedHandlerType());
        
        // 创建线程池
        DynamicThreadPoolExecutor executor = new DynamicThreadPoolExecutor(
                poolName,
                props.getCorePoolSize(),
                props.getMaximumPoolSize(),
                props.getKeepAliveTime(),
                props.getTimeUnit(),
                queue,
                threadFactory,
                handler
        );
        
        // 设置是否允许核心线程超时
        executor.allowCoreThreadTimeOut(props.isAllowCoreThreadTimeOut());
        
        // 注册到监控
        monitor.registerExecutor(executor);
        
        // 注册到映射
        executorMap.put(poolName, executor);
        
        log.info("创建动态线程池: {}, core={}, max={}, queue={}, reject={}",
                poolName, props.getCorePoolSize(), props.getMaximumPoolSize(),
                props.getQueueType(), props.getRejectedHandlerType());
    }

    /**
     * 创建队列
     */
    private BlockingQueue<Runnable> createQueue(TpExecutorProperties props) {
        String queueType = props.getQueueType();
        int capacity = props.getQueueCapacity();
        
        switch (queueType) {
            case "ArrayBlockingQueue":
                return new ArrayBlockingQueue<>(capacity);
            case "SynchronousQueue":
                return new SynchronousQueue<>();
            case "LinkedBlockingQueue":
                return new LinkedBlockingQueue<>(capacity);
            case "PriorityBlockingQueue":
                return new PriorityBlockingQueue<>(capacity);
            case "VariableLinkedBlockingQueue":
            default:
                return new VariableLinkedBlockingQueue<>(capacity);
        }
    }

    /**
     * 创建拒绝策略
     */
    private RejectedExecutionHandler createRejectedHandler(String type) {
        switch (type) {
            case "AbortPolicy":
                return new ThreadPoolExecutor.AbortPolicy();
            case "DiscardPolicy":
                return new ThreadPoolExecutor.DiscardPolicy();
            case "DiscardOldestPolicy":
                return new ThreadPoolExecutor.DiscardOldestPolicy();
            case "CallerRunsPolicy":
            default:
                return new ThreadPoolExecutor.CallerRunsPolicy();
        }
    }

    /**
     * 动态调整线程池参数
     */
    public void updateExecutor(String poolName, TpExecutorProperties newProps) {
        DynamicThreadPoolExecutor executor = executorMap.get(poolName);
        if (executor == null) {
            log.warn("线程池不存在: {}", poolName);
            return;
        }
        
        // 调整核心线程数
        if (newProps.getCorePoolSize() != executor.getCorePoolSize()) {
            executor.setCorePoolSize(newProps.getCorePoolSize());
        }
        
        // 调整最大线程数
        if (newProps.getMaximumPoolSize() != executor.getMaximumPoolSize()) {
            executor.setMaximumPoolSize(newProps.getMaximumPoolSize());
        }
        
        // 调整空闲线程存活时间
        if (newProps.getKeepAliveTime() != executor.getKeepAliveTime(newProps.getTimeUnit())) {
            executor.setKeepAliveTime(newProps.getKeepAliveTime(), newProps.getTimeUnit());
        }
        
        log.info("动态调整线程池: {}", poolName);
    }

    /**
     * 获取线程池
     */
    public DynamicThreadPoolExecutor getExecutor(String poolName) {
        return executorMap.get(poolName);
    }

    /**
     * 获取所有线程池
     */
    public Map<String, DynamicThreadPoolExecutor> getAllExecutors() {
        return executorMap;
    }

    /**
     * 动态线程工厂
     */
    private static class DynamicThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private int threadNumber = 1;

        DynamicThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber++);
            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    /**
     * 可变容量的 LinkedBlockingQueue
     */
    public static class VariableLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {
        
        public VariableLinkedBlockingQueue(int capacity) {
            super(capacity);
        }

        /**
         * 动态调整队列容量（注意：LinkedBlockingQueue 不支持直接调整容量，
         * 这里只是演示，实际需要使用其他方案）
         */
        public void setCapacity(int capacity) {
            // LinkedBlockingQueue 的 capacity 是 final 的，无法修改
            // 实际应用中可以考虑使用其他队列实现或重新创建队列
            log.warn("LinkedBlockingQueue 不支持动态调整容量");
        }
    }
}
