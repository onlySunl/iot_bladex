package org.springblade.core.dynamic.tp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 动态线程池配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.dynamic.tp")
public class DynamicTpProperties {

    /**
     * 是否启用动态线程池
     */
    private boolean enabled = true;

    /**
     * 线程池配置列表
     */
    private List<TpExecutorProperties> executors = new ArrayList<>();

    /**
     * 默认配置
     */
    private TpExecutorProperties defaultExecutor = new TpExecutorProperties();

    /**
     * 监控配置
     */
    private MonitorProperties monitor = new MonitorProperties();

    /**
     * 通知配置
     */
    private NotifyProperties notify = new NotifyProperties();

    /**
     * 单个线程池配置
     */
    @Data
    public static class TpExecutorProperties {

        /**
         * 线程池名称
         */
        private String name = "default";

        /**
         * 核心线程数
         */
        private int corePoolSize = Runtime.getRuntime().availableProcessors();

        /**
         * 最大线程数
         */
        private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;

        /**
         * 空闲线程存活时间
         */
        private long keepAliveTime = 60;

        /**
         * 时间单位
         */
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        /**
         * 队列类型：VariableLinkedBlockingQueue / ArrayBlockingQueue / SynchronousQueue
         */
        private String queueType = "VariableLinkedBlockingQueue";

        /**
         * 队列容量
         */
        private int queueCapacity = 1024;

        /**
         * 是否公平锁
         */
        private boolean fair = false;

        /**
         * 拒绝策略：CallerRunsPolicy / AbortPolicy / DiscardPolicy / DiscardOldestPolicy
         */
        private String rejectedHandlerType = "CallerRunsPolicy";

        /**
         * 是否允许核心线程超时
         */
        private boolean allowCoreThreadTimeOut = false;

        /**
         * 线程名称前缀
         */
        private String threadNamePrefix = "dynamic-tp-";

        /**
         * 是否等待任务完成
         */
        private boolean waitForTasksToCompleteOnShutdown = true;

        /**
         * 等待终止时间（秒）
         */
        private int awaitTerminationSeconds = 60;
    }

    /**
     * 监控配置
     */
    @Data
    public static class MonitorProperties {

        /**
         * 是否启用监控
         */
        private boolean enabled = true;

        /**
         * 监控间隔（秒）
         */
        private int interval = 30;

        /**
         * 是否启用 Micrometer 指标
         */
        private boolean micrometerEnabled = true;

        /**
         * 是否启用日志监控
         */
        private boolean logEnabled = true;
    }

    /**
     * 通知配置
     */
    @Data
    public static class NotifyProperties {

        /**
         * 是否启用通知
         */
        private boolean enabled = false;

        /**
         * 活跃线程阈值比例
         */
        private double activeThreadThreshold = 0.8;

        /**
         * 队列使用率阈值
         */
        private double queueUsageThreshold = 0.8;

        /**
         * 拒绝任务阈值
         */
        private int rejectCountThreshold = 100;
    }
}
