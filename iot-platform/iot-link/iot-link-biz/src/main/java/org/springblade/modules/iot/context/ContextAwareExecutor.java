package org.springblade.modules.iot.context;

import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 上下文感知执行器
 * 用于在异步任务中保持租户上下文
 */
@Component
public class ContextAwareExecutor {
    
    private final Executor executor = Executors.newFixedThreadPool(10);
    
    /**
     * 在上下文中执行任务
     */
    public CompletableFuture<Void> executeWithContext(Runnable task) {
        return CompletableFuture.runAsync(task, executor);
    }
}
