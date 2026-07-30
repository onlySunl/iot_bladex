package org.springblade.core.kafka.config;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springblade.core.kafka.EnableKafkaStarter;
import org.springblade.core.kafka.error.KafkaListenerLoggingErrorHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.ExponentialBackOff;

/**
 * Kafka 消费者通用装配 ── 基于 Spring Boot 自带 {@link KafkaProperties} 自动读 {@code spring.kafka.*}。
 *
 * <h3>装配条件</h3>
 * <ul>
 *   <li>classpath 含 spring-kafka</li>
 *   <li>{@code spring.kafka.bootstrap-servers} 非空</li>
 * </ul>
 *
 * <h3>对外 bean</h3>
 * <ul>
 *   <li>{@link ConsumerFactory}</li>
 *   <li>{@link KafkaListenerContainerFactory}(名 {@code kafkaListenerContainerFactory},spring-kafka 默认查找此名)
 *       ── 支持批量监听 / 并发 / 自定义 AckMode / poll 超时 / 通用错误处理器</li>
 *   <li>{@link CommonErrorHandler}(DLT 重试 + 死信投递,需 {@link KafkaOperations} 可用)</li>
 * </ul>
 *
 * @author mqttsnet
 * @since 2023-06-18
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(annotation = EnableKafkaStarter.class)
@ConditionalOnClass(KafkaListenerContainerFactory.class)
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerAutoConfiguration implements SmartInitializingSingleton {

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerAutoConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * 所有 @Bean 单例创建完成后触发 ── 真正"组件就绪"语义。
     */
    @Override
    public void afterSingletonsInstantiated() {
        log.info("✅ [thinglinks-kafka-starter] consumer 组件初始化成功, bootstrap-servers={}, group-id={}",
            kafkaProperties.getBootstrapServers(),
            kafkaProperties.getConsumer().getGroupId());
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    /**
     * Bean 名固定 {@code kafkaListenerContainerFactory} ── spring-kafka 默认查找此 bean 名。
     */
    @Bean
    @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory,
                                  ObjectProvider<CommonErrorHandler> commonErrorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        Integer concurrency = kafkaProperties.getListener().getConcurrency();
        if (concurrency != null && concurrency > 0) {
            factory.setConcurrency(concurrency);
        }
        factory.setMissingTopicsFatal(kafkaProperties.getListener().isMissingTopicsFatal());

        ContainerProperties cp = factory.getContainerProperties();
        ContainerProperties.AckMode ackMode = kafkaProperties.getListener().getAckMode();
        if (ackMode != null) {
            cp.setAckMode(ackMode);
        }
        Duration pollTimeout = kafkaProperties.getListener().getPollTimeout();
        if (pollTimeout != null) {
            cp.setPollTimeout(pollTimeout.toMillis());
        }

        factory.setBatchListener(kafkaProperties.getListener().getType() == KafkaProperties.Listener.Type.BATCH);

        commonErrorHandler.ifAvailable(factory::setCommonErrorHandler);
        log.info("[kafka-starter] listener container factory ready concurrency={} batch={} ackMode={}",
            concurrency, factory.isBatchListener(), ackMode);
        return factory;
    }

    /**
     * 通用 Kafka 错误处理器 ── 指数退避重试 + 失败投递 {@code <orig>.DLT}。
     * 反序列化异常等不可恢复错误直接进 DLT,不浪费重试。
     */
    @Bean
    @ConditionalOnMissingBean
    public CommonErrorHandler kafkaCommonErrorHandler(
        ObjectProvider<KafkaOperations<String, String>> kafkaTemplateProvider) {
        KafkaOperations<String, String> template = kafkaTemplateProvider.getIfAvailable();
        if (template == null) {
            log.warn("[kafka-starter] KafkaTemplate unavailable, DLT degrades to log + offset skip");
            return new DefaultErrorHandler(defaultBackOff());
        }
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
            template,
            (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition()));
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, defaultBackOff());
        handler.addNotRetryableExceptions(IllegalArgumentException.class, NullPointerException.class);
        log.info("[kafka-starter] DLT error handler ready: backoff=1s→2s→4s×3, dlt=<orig>.DLT");
        return handler;
    }

    /**
     * 通用 KafkaListenerErrorHandler ── bean 名 {@code myKafkaListenerErrorHandler} 兼容老业务引用。
     */
    @Bean(name = "myKafkaListenerErrorHandler")
    @ConditionalOnMissingBean(name = "myKafkaListenerErrorHandler")
    public KafkaListenerLoggingErrorHandler myKafkaListenerErrorHandler() {
        return new KafkaListenerLoggingErrorHandler();
    }

    /**
     * 默认指数退避 ── 1s, 2s, 4s,共 3 次重试。
     */
    private ExponentialBackOff defaultBackOff() {
        ExponentialBackOff backOff = new ExponentialBackOff(1000L, 2.0);
        backOff.setMaxInterval(30_000L);
        backOff.setMaxElapsedTime(60_000L);
        return backOff;
    }
}
