package org.springblade.core.rocketmq;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springblade.core.rocketmq.config.RocketmqAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 启用 thinglinks-rocketmq-starter 装配 ── opt-in 注解。
 *
 * <p>util 是底层公共工程,某微服务可能因传递依赖引到 starter 但<b>实际不使用</b>。
 * 必须在 Spring Boot 主类标本注解才装配 {@link RocketmqAutoConfiguration}。
 *
 * <pre>{@code
 * @SpringBootApplication
 * @EnableRocketmqStarter
 * public class XxxServerApplication { ... }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-05-11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RocketmqAutoConfiguration.class)
public @interface EnableRocketmqStarter {
}
