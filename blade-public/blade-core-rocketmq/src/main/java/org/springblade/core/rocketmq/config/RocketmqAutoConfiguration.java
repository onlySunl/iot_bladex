package org.springblade.core.rocketmq.config;

import org.springblade.core.rocketmq.EnableRocketmqStarter;
import org.springblade.core.rocketmq.producer.RocketmqTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RocketMQ starter 装配入口 ── 主类标 {@link EnableRocketmqStarter} 时才装配。
 * <p>底层 client 由 rocketmq-spring 根据 {@code access-channel} 自动选择(Apache / 阿里云),本类零品牌分支。
 * <p>所有 singleton 装配完成后(Container start 之前),把 upstream producer 和所有 consumer 的
 * {@code mqClientApiTimeout} 提到 {@value #MQ_CLIENT_API_TIMEOUT_MS}ms ── 补 upstream 默认 3000ms
 * 太紧导致跨公网首次 send / consumer 启动撞 namesrv 查询超时的 gap
 * (<a href="https://github.com/apache/rocketmq-spring/issues/485">Issue #485</a> 未修)。
 *
 * @author mqttsnet
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(annotation = EnableRocketmqStarter.class)
@ConditionalOnClass(RocketMQTemplate.class)
@ConditionalOnProperty(prefix = "rocketmq", name = "name-server")
@RequiredArgsConstructor
public class RocketmqAutoConfiguration implements SmartInitializingSingleton {

    /**
     * client 内部 RPC 超时 ── 控制 NameServer 路由查询 / 心跳 / trace 投递。
     * 给跨公网 / 弱网 / 容器 cold start 留足余量;内网正常情况几十 ms 即返回,不会触发。
     */
    private static final int MQ_CLIENT_API_TIMEOUT_MS = 10_000;

    private final ObjectProvider<RocketMQTemplate> rocketMQTemplateProvider;
    private final ObjectProvider<DefaultRocketMQListenerContainer> consumerContainerProvider;

    @Override
    public void afterSingletonsInstantiated() {
        Optional.ofNullable(rocketMQTemplateProvider.getIfAvailable())
                .map(RocketMQTemplate::getProducer)
                .ifPresent(this::patchProducer);
        AtomicInteger consumerCount = new AtomicInteger();
        consumerContainerProvider.forEach(container -> {
            DefaultMQPushConsumer consumer = container.getConsumer();
            if (consumer != null) {
                consumer.setMqClientApiTimeout(MQ_CLIENT_API_TIMEOUT_MS);
                consumerCount.incrementAndGet();
            }
        });
        log.info("✅ [thinglinks-rocketmq-starter] 组件初始化成功 (mqClientApiTimeout={}ms, consumers={})", MQ_CLIENT_API_TIMEOUT_MS, consumerCount.get());
    }

    private void patchProducer(DefaultMQProducer producer) {
        producer.setMqClientApiTimeout(MQ_CLIENT_API_TIMEOUT_MS);
    }

    @Bean
    @ConditionalOnMissingBean
    public RocketmqTemplate rocketmqTemplate(RocketMQTemplate raw) {
        return new RocketmqTemplate(raw);
    }
}
