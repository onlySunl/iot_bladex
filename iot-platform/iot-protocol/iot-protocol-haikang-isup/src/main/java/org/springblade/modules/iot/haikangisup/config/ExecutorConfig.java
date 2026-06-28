package org.springblade.modules.iot.haikangisup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置
 *
 * @author fengcheng
 */
@Configuration
public class ExecutorConfig {

	@Bean("haikangTaskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(8);
		executor.setMaxPoolSize(32);
		executor.setQueueCapacity(200);
		executor.setKeepAliveSeconds(60);
		executor.setThreadNamePrefix("haikang-async-");
		executor.initialize();
		return executor;
	}
}
