package org.springblade.core.dynamic.tp.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.context.ContextUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 上下文感知的异步执行工具类
 * 用于在异步任务中保持ThreadLocal上下文，如用户信息、租户信息等
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/20
 */
@Getter
@Slf4j
@Component
public class ContextAwareExecutor {
    /**
     * 获取默认线程池执行器
     */
    private final ThreadPoolExecutor defaultExecutor;
    /**
     * 获取默认的并发限制器
     */
    private final Semaphore defaultConcurrencyLimiter;

    /**
     * 构造函数
     *
     * @param defaultExecutor 默认线程池执行器
     */
    public ContextAwareExecutor(@Qualifier("systemDefaultExecutor") ThreadPoolExecutor defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
        this.defaultConcurrencyLimiter = new Semaphore(100);
    }

    /**
     * 使用默认线程池和并发限制执行异步任务
     *
     * @param task 需要执行的任务
     * @param <T>  返回类型
     * @return {@link CompletableFuture<T>} 任务执行结果
     */
    public <T> CompletableFuture<T> executeWithContext(Supplier<T> task) {
        return executeWithContext(task, defaultExecutor, defaultConcurrencyLimiter);
    }

    /**
     * 使用指定线程池执行异步任务（无并发限制）
     *
     * @param task     需要执行的任务
     * @param executor 线程池执行器
     * @param <T>      返回类型
     * @return {@link CompletableFuture<T>} 任务执行结果
     */
    public <T> CompletableFuture<T> executeWithContext(Supplier<T> task, ThreadPoolExecutor executor) {
        return executeWithContext(task, executor, null);
    }

    /**
     * 使用指定线程池和并发限制执行异步任务
     *
     * @param task             需要执行的任务
     * @param executor         线程池执行器
     * @param concurrencyLimit 并发限制器，为null时不限制并发
     * @param <T>              返回类型
     * @return {@link CompletableFuture<T>} 任务执行结果
     */
    public <T> CompletableFuture<T> executeWithContext(Supplier<T> task,
                                                       ThreadPoolExecutor executor,
                                                       Semaphore concurrencyLimit) {
        Map<String, String> localMap = ContextUtil.getLocalMap();
        String currentThreadName = Thread.currentThread().getName();

        return CompletableFuture.supplyAsync(() -> {
            String asyncThreadName = Thread.currentThread().getName();
            boolean acquired = false;
            Map<String, String> originalContext = null;

            try {
                // 保存原始上下文（用于恢复）
                originalContext = ContextUtil.getLocalMap();

                // 应用并发限制
                if (concurrencyLimit != null) {
                    concurrencyLimit.acquire();
                    acquired = true;
                }

                // 设置新的上下文
                ContextUtil.setLocalMap(localMap);

                if (log.isDebugEnabled()) {
                    log.debug("Context transferred from [{}] to [{}]", currentThreadName, asyncThreadName);
                }

                return task.get();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("Task interrupted during execution", e);
                throw new RuntimeException("Task interrupted", e);
            } catch (RuntimeException e) {
                log.error("Runtime error executing task in async context", e);
                throw e;
            } catch (Exception e) {
                log.error("Unexpected error executing task in async context", e);
                throw new RuntimeException("Unexpected error in async execution", e);
            } finally {
                try {
                    // 恢复原始上下文而不是完全清理
                    if (originalContext != null) {
                        ContextUtil.setLocalMap(originalContext);
                    } else {
                        ContextUtil.remove();
                    }

                    if (log.isDebugEnabled()) {
                        log.debug("Context restored on [{}]", asyncThreadName);
                    }
                } catch (Exception e) {
                    log.warn("Failed to restore context on thread [{}]", asyncThreadName, e);
                }

                // 释放并发限制（acquired为true时concurrencyLimit一定不为null）
                if (acquired) {
                    try {
                        concurrencyLimit.release();
                    } catch (Exception e) {
                        log.warn("Failed to release semaphore on thread [{}]", asyncThreadName, e);
                    }
                }
            }
        }, executor);
    }

    /**
     * 执行无返回值的异步任务
     *
     * @param runnable 需要执行的任务
     * @return {@link CompletableFuture<Void>} 任务执行结果
     */
    public CompletableFuture<Void> executeWithContext(Runnable runnable) {
        return executeWithContext(() -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 批量执行异步任务
     *
     * @param tasks 需要执行的任务列表
     * @param <T>   返回类型
     * @return 所有任务的CompletableFuture列表
     */
    public <T> List<CompletableFuture<T>> executeBatchWithContext(List<Supplier<T>> tasks) {
        List<CompletableFuture<T>> futures = new ArrayList<>(tasks.size());

        for (Supplier<T> task : tasks) {
            futures.add(executeWithContext(task));
        }

        return futures;
    }

    /**
     * 批量执行异步任务并等待所有完成
     *
     * @param tasks 需要执行的任务列表
     * @param <T>   返回类型
     * @return 所有任务结果的CompletableFuture
     */
    public <T> CompletableFuture<List<T>> executeBatchAndWaitAll(List<Supplier<T>> tasks) {
        List<CompletableFuture<T>> futures = executeBatchWithContext(tasks);
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> {
                    List<T> results = new ArrayList<>(futures.size());
                    for (CompletableFuture<T> future : futures) {
                        results.add(future.join());
                    }
                    return results;
                });
    }

}
