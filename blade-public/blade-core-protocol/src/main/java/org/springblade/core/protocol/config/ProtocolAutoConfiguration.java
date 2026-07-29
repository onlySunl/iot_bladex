package org.springblade.core.protocol.config;

import org.springblade.core.protocol.codec.JsonProtocolCodec;
import org.springblade.core.protocol.codec.ProtocolCodec;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 协议自动配置
 *
 * @author Chill
 */
@Configuration
public class ProtocolAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ProtocolCodec jsonProtocolCodec() {
        return new JsonProtocolCodec();
    }
}
