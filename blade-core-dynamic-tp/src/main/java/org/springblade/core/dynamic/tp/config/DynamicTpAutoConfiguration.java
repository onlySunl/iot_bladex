package org.springblade.core.dynamic.tp.config;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.dynamic.tp.adapter.DynamicTpAdapter;
import org.springblade.core.dynamic.tp.executor.DynamicThreadPoolExecutor;
import org.springblade.core.dynamic.tp.monitor.DynamicTpMonitor;
import org.springblade.core.dynamic.tp.properties.DynamicTpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态线程池自动配置
 *
 * @author Chill
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DynamicTpProperties.class)
@ConditionalOnProperty(prefix = "blade.dynamic.tp", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicTpAutoConfiguration {

    @Autowired(required = false)
    private MeterRegistry meterRegistry;

    /**
     * 线程池注册表
     */
    private final Map<String, DynamicThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();

    @Bean
    @ConditionalOnMissingBean
    public DynamicTpMonitor dynamicTpMonitor(DynamicTpProperties properties) {
        return new DynamicTpMonitor(properties, meterRegistry);
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicTpAdapter dynamicTpAdapter(DynamicTpProperties properties,
                                             DynamicTpMonitor monitor) {
        return new DynamicTpAdapter(properties, monitor, executorMap);
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
     * 注册线程池
     */
    public void registerExecutor(String poolName, DynamicThreadPoolExecutor executor) {
        executorMap.put(poolName, executor);
        log.info("注册动态线程池: {}", poolName);
    }
}
