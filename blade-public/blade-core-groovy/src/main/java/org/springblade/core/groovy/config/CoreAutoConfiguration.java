package org.springblade.core.groovy.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springblade.core.groovy.compiler.DynamicCodeCompiler;
import org.springblade.core.groovy.compiler.impl.GroovyCompiler;
import org.springblade.core.groovy.config.properties.GroovyEngineProperties;
import org.springblade.core.groovy.entity.ScriptEntry;
import org.springblade.core.groovy.executor.EngineExecutor;
import org.springblade.core.groovy.executor.impl.DefaultEngineExecutor;
import org.springblade.core.groovy.helper.RefreshScriptHelper;
import org.springblade.core.groovy.loader.ScriptLoader;
import org.springblade.core.groovy.registry.ScriptRegistry;
import org.springblade.core.groovy.registry.impl.DefaultScriptRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 核心自动配置类
 * {@link GroovyEngineProperties#isEnable()}
 * </p>
 *
 * @author mqttsnet 2025/03/18 18:11
 */
@Configuration
public class CoreAutoConfiguration {

    /**
     * groovy class编译器
     */
    @Bean
    @ConditionalOnMissingBean(DynamicCodeCompiler.class)
    public DynamicCodeCompiler groovyCompiler() {
        return new GroovyCompiler();
    }

    /**
     * 可由使用方动态替换
     */
    @Bean(name = "thinglinksGroovyScriptEngineCache")
    @ConditionalOnMissingBean(name = "thinglinksGroovyScriptEngineCache", value = {Cache.class})
    public @NonNull Cache<String, ScriptEntry> thinglinksGroovyScriptEngineCache(GroovyEngineProperties groovyEngineProperties) {
        return Caffeine.newBuilder()
                .expireAfterWrite(groovyEngineProperties.getCacheExpireAfterWrite(), TimeUnit.MINUTES)
                .initialCapacity(groovyEngineProperties.getCacheInitialCapacity())
                .maximumSize(groovyEngineProperties.getCacheMaximumSize())
                .build();
    }

    /**
     * 脚本注册中心，ScriptLoader 由使用方（如 iot-rule）提供，required=false 允许启动时不注入
     */
    @Bean
    @ConditionalOnMissingBean(ScriptRegistry.class)
    public ScriptRegistry scriptRegistry(
            @Autowired(required = false) ScriptLoader scriptLoader,
            @Qualifier("thinglinksGroovyScriptEngineCache") Cache<String, ScriptEntry> cache) {
        return new DefaultScriptRegistry(scriptLoader, cache);
    }

    /**
     * 执行引擎
     */
    @Bean
    @ConditionalOnMissingBean(EngineExecutor.class)
    public EngineExecutor defaultEngineExecutor(
            @Autowired(required = false) ScriptRegistry scriptRegistry) {
        return new DefaultEngineExecutor(scriptRegistry);
    }

    /**
     * 注入刷新groovy脚本助手
     */
    @Bean
    @ConditionalOnMissingBean(RefreshScriptHelper.class)
    public RefreshScriptHelper refreshScriptHelper() {
        return new RefreshScriptHelper();
    }
}
