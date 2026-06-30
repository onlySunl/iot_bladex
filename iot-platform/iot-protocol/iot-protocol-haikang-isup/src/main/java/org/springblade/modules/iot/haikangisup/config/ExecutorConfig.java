package org.springblade.modules.iot.haikangisup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(32);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("stream-async-");
        // 设置线程池关闭时等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置等待时间（秒）
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
