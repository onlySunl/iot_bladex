package org.springblade.core.chatgpt.config;

import org.springblade.core.chatgpt.client.ChatGptClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * ChatGPT 自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(ChatGptProperties.class)
@ConditionalOnProperty(prefix = "blade.chatgpt", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ChatGptAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ChatGptClient chatGptClient(ChatGptProperties properties) {
        return new ChatGptClient(properties);
    }

}
