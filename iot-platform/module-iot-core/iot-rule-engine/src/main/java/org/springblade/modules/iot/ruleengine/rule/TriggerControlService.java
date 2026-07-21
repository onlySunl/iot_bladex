package org.springblade.modules.iot.ruleengine.rule;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RMap;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springblade.modules.iot.api.rule.dto.TriggerOptions;
import org.springblade.modules.iot.common.context.TenantContextHolder;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 规则触发控制器：限频、延时触发、告警自动恢复（基于Redisson延迟队列）
 * 功能：
 * 1. 最小间隔限流拦截重复触发
 * 2. 延时动作延迟队列投递执行
 * 3. 告警静默延时自动恢复
 * 4. 多租户TTL上下文透传
 */
@Slf4j
@Component
public class TriggerControlService implements InitializingBean {

    // Redis Key 常量统一管理
    private static final String STATE_KEY = "rule:trigger:state";
    private static final String ACTION_QUEUE = "rule:trigger:action:q";
    private static final String RECOVER_QUEUE = "rule:trigger:recover:q";
    private static final String RATE_LIMITER_KEY_PREFIX = "rule:trigger:limit:";
    // 状态缓存过期时间 30天
    private static final long STATE_EXPIRE_SEC = 30 * 24 * 60 * 60L;
    // 线程池等待关闭超时
    private static final long SHUTDOWN_WAIT_SEC = 3L;

    private RedissonClient redissonClient;
    private final RuleManager ruleManager;
    private final RuleExecutor ruleExecutor;

    public TriggerControlService(@Autowired(required = false) RedissonClient redissonClient,
                                  @Lazy RuleManager ruleManager,
                                  @Lazy RuleExecutor ruleExecutor) {
        this.redissonClient = redissonClient;
        this.ruleManager = ruleManager;
        this.ruleExecutor = ruleExecutor;
    }

    private RBlockingQueue<TriggerJob> actionQueue;
    private RDelayedQueue<TriggerJob> actionDelayedQueue;
    private RBlockingQueue<TriggerJob> recoverQueue;
    private RDelayedQueue<TriggerJob> recoverDelayedQueue;
    private ExecutorService consumerPool;
    // 销毁标记，防止重复执行销毁逻辑
    private volatile boolean destroyed = false;

    @Override
    public void afterPropertiesSet() {
        if (redissonClient == null) {
            log.warn("RedissonClient 不可用，TriggerControlService 限频/延时/告警恢复功能已禁用");
            return;
        }
        // 初始化阻塞队列与延迟队列
        actionQueue = redissonClient.getBlockingQueue(ACTION_QUEUE);
        actionDelayedQueue = redissonClient.getDelayedQueue(actionQueue);
        recoverQueue = redissonClient.getBlockingQueue(RECOVER_QUEUE);
        recoverDelayedQueue = redissonClient.getDelayedQueue(recoverQueue);

        // 构建原生线程池 + TTL包装，透传租户上下文
        ExecutorService rawExecutor = Executors.newFixedThreadPool(2, r -> {
            Thread thread = new Thread(r);
            thread.setName("rule-trigger-consumer");
            thread.setDaemon(true);
            return thread;
        });
        consumerPool = TtlExecutors.getTtlExecutorService(rawExecutor);

        // 启动两个消费线程：动作队列 / 恢复队列
        consumerPool.submit(() -> consume(actionQueue, false));
        consumerPool.submit(() -> consume(recoverQueue, true));
        log.info("TriggerControlService 队列消费线程启动完成");
    }

    /**
     * 队列消费循环
     */
    private void consume(RBlockingQueue<TriggerJob> queue, boolean recovery) {
        String queueType = recovery ? "恢复告警队列" : "规则动作队列";
        while (!Thread.currentThread().isInterrupted() && !destroyed) {
            try {
                TriggerJob job = queue.poll(1, TimeUnit.SECONDS);
                if (job == null) {
                    continue;
                }
                // 绑定租户上下文
                TenantContextHolder.setTenantId(job.getTenantId());
                processJob(job, recovery);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("{} 消费线程被中断，退出循环", queueType);
                break;
            } catch (org.redisson.RedissonShutdownException e) {
                log.info("Redisson客户端关闭，停止{}消费", queueType);
                break;
            } catch (Throwable e) {
                log.error("{} 消费任务异常", queueType, e);
            } finally {
                TenantContextHolder.clear();
            }
        }
        log.info("{} 消费线程正常退出", queueType);
    }

    /**
     * 处理单个队列任务
     */
    private void processJob(TriggerJob job, boolean recoveryJob) {
        Rule rule = ruleManager.getRule(job.getRuleId());
        if (rule == null) {
            log.warn("规则ID:{} 缓存不存在，丢弃{}任务", job.getRuleId(), recoveryJob ? "告警恢复" : "触发动作");
            return;
        }
        ThingModelMessage msg = JsonUtils.parseObject(job.getMessageJson(), ThingModelMessage.class);
        if (recoveryJob) {
            TriggerState state = getState(rule.getId());
            // 未告警直接跳过恢复
            if (state == null || !state.isAlerting()) {
                log.info("规则{} 当前无告警，跳过恢复任务", rule.getId());
                return;
            }
            // token防重复恢复校验
            if (job.getRecoverToken() != null && !job.getRecoverToken().equals(state.getRecoverToken())) {
                log.info("规则{} 恢复Token不匹配，作废旧恢复任务", rule.getId());
                return;
            }
            log.info("执行规则{} 告警恢复动作", rule.getId());
            ruleExecutor.executeActions(rule, msg, true);
        } else {
            log.info("执行规则{} 延时触发动作", rule.getId());
            ruleExecutor.executeActions(rule, msg, false);
            markTriggered(rule.getId());
        }
    }

    /**
     * 流量限流校验：最小触发间隔控制
     */
    public boolean passRateLimit(Long ruleId, TriggerOptions options) {
        if (redissonClient == null) {
            return true;
        }
        if (options == null || options.getMinIntervalSec() == null || options.getMinIntervalSec() <= 0) {
            return true;
        }
        int intervalSec = options.getMinIntervalSec();
        RRateLimiter limiter = redissonClient.getRateLimiter(RATE_LIMITER_KEY_PREFIX + ruleId);
        // 仅首次初始化，避免重复修改限流配置
        if (!limiter.isExists()) {
            limiter.trySetRate(RateType.OVERALL, 1, intervalSec, RateIntervalUnit.SECONDS);
        }
        boolean acquire = limiter.tryAcquire();
        if (!acquire) {
            log.info("规则{} 触发间隔不足{}秒，限流拦截", ruleId, intervalSec);
        }
        return acquire;
    }

    /**
     * 标记规则已触发告警
     */
    public void markTriggered(Long ruleId) {
        TriggerState state = getState(ruleId);
        if (state == null) {
            state = new TriggerState();
        }
        state.setAlerting(true);
        state.setRecoverToken(null);
        saveState(ruleId, state);
        log.debug("规则{} 标记为告警中", ruleId);
    }

    /**
     * 标记规则告警已解除
     */
    public void markRecovered(Long ruleId) {
        TriggerState state = getState(ruleId);
        if (state == null) {
            state = new TriggerState();
        }
        state.setAlerting(false);
        state.setRecoverToken(null);
        saveState(ruleId, state);
        log.debug("规则{} 标记告警已恢复", ruleId);
    }

    /**
     * 执行规则动作：区分即时执行 / 延时入队
     */
    public void executeAction(Rule rule, ThingModelMessage msg, TriggerOptions options) {
        if (redissonClient == null) {
            ruleExecutor.executeActions(rule, msg, false);
            return;
        }
        long delayMs = 0L;
        if (options != null && options.getDelaySec() != null && options.getDelaySec() > 0) {
            delayMs = options.getDelaySec() * 1000L;
        }
        if (delayMs > 0) {
            TriggerJob job = new TriggerJob(rule.getId(), JsonUtils.toJsonString(msg), false, null, rule.getTenantId());
            actionDelayedQueue.offer(job, delayMs, TimeUnit.MILLISECONDS);
            log.info("规则{} 延时{}ms触发动作，入延迟队列", rule.getId(), delayMs);
        } else {
            ruleExecutor.executeActions(rule, msg, false);
            markTriggered(rule.getId());
        }
    }

    /**
     * 调度告警自动恢复延时任务
     */
    public void scheduleRecoverIfNeeded(Rule rule, ThingModelMessage msg, TriggerOptions options) {
        if (redissonClient == null) {
            return;
        }
        if (options == null || options.getEnableAlertRecover() == null || !options.getEnableAlertRecover()) {
            return;
        }
        TriggerState state = getState(rule.getId());
        if (state == null || !state.isAlerting()) {
            return;
        }
        long delayMs = 0L;
        if (options.getRecoverQuietSec() != null && options.getRecoverQuietSec() > 0) {
            delayMs = options.getRecoverQuietSec() * 1000L;
        }
        String token = UUID.randomUUID().toString();
        state.setRecoverToken(token);
        saveState(rule.getId(), state);

        if (delayMs > 0) {
            TriggerJob job = new TriggerJob(rule.getId(), JsonUtils.toJsonString(msg), true, token, rule.getTenantId());
            recoverDelayedQueue.offer(job, delayMs, TimeUnit.MILLISECONDS);
            log.info("规则{} 延时{}ms自动恢复告警，Token:{}", rule.getId(), delayMs, token);
        } else {
            log.info("规则{} 立即执行告警恢复", rule.getId());
            ruleExecutor.executeActions(rule, msg, true);
        }
    }

    /**
     * 取消待执行的恢复任务（更新Token作废旧任务）
     */
    public void cancelRecover(Long ruleId) {
        TriggerState state = getState(ruleId);
        if (state != null) {
            state.setRecoverToken(UUID.randomUUID().toString());
            saveState(ruleId, state);
            log.info("规则{} 作废所有待恢复任务，刷新恢复Token", ruleId);
        }
    }

    /**
     * 读取规则触发状态
     */
    private TriggerState getState(Long ruleId) {
        if (destroyed || redissonClient == null) {
            return null;
        }
        RMap<Long, String> stateMap = redissonClient.getMap(STATE_KEY);
        String json = stateMap.get(ruleId);
        if (json == null) {
            return null;
        }
        return JsonUtils.parseObject(json, TriggerState.class);
    }

    /**
     * 保存规则触发状态，设置过期时间
     */
    private void saveState(Long ruleId, TriggerState state) {
        if (destroyed || redissonClient == null) {
            return;
        }
        RMap<Long, String> stateMap = redissonClient.getMap(STATE_KEY);
        stateMap.put(ruleId, JsonUtils.toJsonString(state));
        stateMap.expire(STATE_EXPIRE_SEC, TimeUnit.SECONDS);
    }

    /**
     * 删除规则所有Redis缓存：限流器、触发状态
     */
    public void clearRuleCache(Long ruleId) {
        if (destroyed || redissonClient == null) {
            log.warn("服务已销毁，跳过清理规则{}缓存", ruleId);
            return;
        }
        try {
            // 删除限流对象
            RRateLimiter limiter = redissonClient.getRateLimiter(RATE_LIMITER_KEY_PREFIX + ruleId);
            limiter.delete();
            // 删除状态缓存
            RMap<Long, String> stateMap = redissonClient.getMap(STATE_KEY);
            stateMap.remove(ruleId);
            log.info("清理规则{} Redis缓存完成", ruleId);
        } catch (Throwable e) {
            log.warn("清理规则{} Redis缓存异常", ruleId, e);
        }
    }

    /**
     * 服务销毁，关闭队列、线程池释放资源
     * 增加销毁标记，防止Redisson已关闭后继续操作Redis产生连锁关闭异常
     */
    @PreDestroy
    public void destroy() {
        if (destroyed) {
            return;
        }
        destroyed = true;
        log.info("TriggerControlService 开始销毁资源");

        // 1. 先关闭消费线程池，停止新任务拉取
        if (consumerPool != null) {
            consumerPool.shutdownNow();
            try {
                if (!consumerPool.awaitTermination(SHUTDOWN_WAIT_SEC, TimeUnit.SECONDS)) {
                    log.warn("消费线程池未及时关闭，强制终止");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("等待线程池关闭被中断");
            }
        }

        // 2. 延迟队列销毁（增加判空，避免Redisson提前销毁空指针）
        try {
            if (actionDelayedQueue != null) {
                actionDelayedQueue.destroy();
            }
            if (recoverDelayedQueue != null) {
                recoverDelayedQueue.destroy();
            }
        } catch (org.redisson.RedissonShutdownException e) {
            log.info("Redisson 已提前关闭，无需销毁延迟队列");
        } catch (Throwable e) {
            log.warn("销毁延迟队列出现异常", e);
        }
        log.info("TriggerControlService 资源销毁完成");
    }
}