package org.springblade.core.chatgpt.config;


import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.function.KeyStrategyFunction;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springblade.core.chatgpt.config.properties.OpenAiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * OpenAi 基础配置类
 *
 * @author mqttsnet
 * @date 2023-12-09 22:53
 */
@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
@ConditionalOnClass(OpenAiStreamClient.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class OpenAiAutoConfiguration {
    private final OpenAiProperties openAiProperties;

    @Autowired
    public OpenAiAutoConfiguration(OpenAiProperties openAiProperties) {
        this.openAiProperties = openAiProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new OpenAILogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "openai", name = "api-key")
    public OpenAiClient openAiClient(OkHttpClient okHttpClient) {
        String apiHost = Optional.ofNullable(openAiProperties.getApiHost())
                .orElse("https://api.openai.com/");

        List<String> apiKey = openAiProperties.getApiKey();

        KeyStrategyFunction<List<String>, String> keyStrategy = apiKey.size() > 1
                ? new KeyRandomStrategy() : new FirstKeyStrategy();

        return OpenAiClient.builder()
                .apiHost(apiHost)
                .apiKey(apiKey)
                .keyStrategy(keyStrategy)
                .okHttpClient(okHttpClient)
                .build();
    }
}
