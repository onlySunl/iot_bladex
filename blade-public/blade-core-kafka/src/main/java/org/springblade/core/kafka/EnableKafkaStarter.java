package org.springblade.core.kafka;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import org.springblade.core.kafka.config.KafkaConsumerAutoConfiguration;
import org.springblade.core.kafka.config.KafkaProducerAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 启用 thinglinks-kafka-starter 装配 ── opt-in 注解。
 *
 * <p>util 是底层公共工程,某微服务可能因传递依赖引到 starter 但<b>实际不使用</b>。
 * 必须在 Spring Boot 主类标本注解才装配 {@link KafkaProducerAutoConfiguration} / {@link KafkaConsumerAutoConfiguration}。
 *
 * <pre>{@code
 * @SpringBootApplication
 * @EnableKafkaStarter
 * public class XxxServerApplication { ... }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-05-11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KafkaProducerAutoConfiguration.class, KafkaConsumerAutoConfiguration.class})
public @interface EnableKafkaStarter {
}
