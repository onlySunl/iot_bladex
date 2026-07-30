package org.springblade.core.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.kafka.EnableKafkaStarter;
import org.springblade.core.kafka.producer.KafkaProducerService;
import org.springblade.core.kafka.producer.KafkaSendResultHandler;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.util.StringUtils;

/**
 * Kafka 生产者通用装配 ── 基于 Spring Boot 自带 {@link KafkaProperties} 自动读 {@code spring.kafka.*}。
 *
 * <h3>装配条件</h3>
 * <ul>
 *   <li>classpath 含 spring-kafka</li>
 *   <li>{@code spring.kafka.bootstrap-servers} 非空</li>
 * </ul>
 *
 * <h3>对外 bean</h3>
 * <ul>
 *   <li>{@link ProducerFactory} ── 配 {@code transaction-id-prefix} 时自动开启事务</li>
 *   <li>{@link KafkaTransactionManager} ── 仅当配了事务前缀时装配</li>
 *   <li>{@link KafkaTemplate}({@code thingLinksKafkaTemplate}) ── 自动 attach {@link KafkaSendResultHandler}</li>
 *   <li>{@link KafkaSendResultHandler} ── 默认发送回调,记录成功 / 失败</li>
 *   <li>{@link KafkaProducerService} ── 业务侧统一调用入口</li>
 * </ul>
 *
 * <p>业务侧自定义可通过 {@link ConditionalOnMissingBean} 覆盖任一 bean。
 *
 * @author mqttsnet
 * @since 2023-06-18
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(annotation = EnableKafkaStarter.class)
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaProducerAutoConfiguration implements SmartInitializingSingleton {

    private final KafkaProperties kafkaProperties;

    public KafkaProducerAutoConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * 所有 @Bean 单例创建完成后触发 ── 真正"组件就绪"语义。
     */
    @Override
    public void afterSingletonsInstantiated() {
        log.info("✅ [thinglinks-kafka-starter] producer 组件初始化成功, bootstrap-servers={}",
            kafkaProperties.getBootstrapServers());
    }

    @Bean
    @ConditionalOnMissingBean
    public ProducerFactory<String, String> producerFactory() {
        DefaultKafkaProducerFactory<String, String> factory =
            new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
        String txPrefix = kafkaProperties.getProducer().getTransactionIdPrefix();
        if (StringUtils.hasText(txPrefix)) {
            factory.setTransactionIdPrefix(txPrefix);
            log.info("[kafka-starter] producer transaction enabled, prefix={}", txPrefix);
        }
        return factory;
    }

    /**
     * 仅在配了事务前缀时装配,避免无事务场景产生无用 bean。
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.kafka.producer", name = "transaction-id-prefix")
    public KafkaTransactionManager<String, String> kafkaTransactionManager(ProducerFactory<String, String> producerFactory) {
        log.info("[kafka-starter] KafkaTransactionManager initialized");
        return new KafkaTransactionManager<>(producerFactory);
    }

    /**
     * 默认 ProducerListener:统一记录发送成功 / 失败日志。
     */
    @Bean
    @ConditionalOnMissingBean
    public KafkaSendResultHandler kafkaSendResultHandler() {
        return new KafkaSendResultHandler();
    }

    /**
     * Template bean 名 {@code thingLinksKafkaTemplate} 兼容老业务 @Qualifier;自动 attach 回调。
     */
    @Bean(name = "thingLinksKafkaTemplate")
    @ConditionalOnMissingBean(name = "thingLinksKafkaTemplate")
    public KafkaTemplate<String, String> thingLinksKafkaTemplate(ProducerFactory<String, String> producerFactory,
                                                                 KafkaSendResultHandler sendResultHandler) {
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);
        template.setProducerListener(sendResultHandler);
        // 工厂配置了 transaction-id-prefix 时,允许事务上下文之外的普通 send 以非事务方式发送,
        // 避免开启事务后全局 send 被强制要求事务上下文(否则抛 No transaction is in process)
        template.setAllowNonTransactional(true);
        String defaultTopic = kafkaProperties.getTemplate().getDefaultTopic();
        if (StringUtils.hasText(defaultTopic)) {
            template.setDefaultTopic(defaultTopic);
            log.info("[kafka-starter] KafkaTemplate default-topic={}", defaultTopic);
        }
        return template;
    }

    /**
     * 业务统一调用入口。
     */
    @Bean
    @ConditionalOnMissingBean
    public KafkaProducerService kafkaProducerService(KafkaTemplate<String, String> thingLinksKafkaTemplate) {
        return new KafkaProducerService(thingLinksKafkaTemplate);
    }
}
