package org.springblade.core.groovy.config;

import org.springblade.core.groovy.alarm.HotLoadingGroovyScriptAlarm;
import org.springblade.core.groovy.annotation.ConditionalOnExistingProperty;
import org.springblade.core.groovy.config.properties.GroovyEngineProperties;
import org.springblade.core.groovy.executor.AutoRefreshScriptExecutor;
import org.springblade.core.groovy.helper.ApplicationContextHelper;
import org.springblade.core.groovy.helper.RefreshScriptHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置类
 *
 * @author mqttsnet 2025/03/18 14:25
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {GroovyEngineProperties.class})
@ConditionalOnExistingProperty(property = GroovyEngineProperties.PREFIX + ".enable", value = "true")
public class ThinglinksGroovyEngineCoreAutoConfiguration {

    /**
     * 自动刷新脚本executor
     */
    @Bean
    public AutoRefreshScriptExecutor autoRefreshScriptExecutor(GroovyEngineProperties groovyEngineProperties,
                                                               RefreshScriptHelper refreshScriptHelper) {

        return new AutoRefreshScriptExecutor(groovyEngineProperties, refreshScriptHelper);
    }

    @Bean
    @ConditionalOnMissingBean(HotLoadingGroovyScriptAlarm.class)
    public HotLoadingGroovyScriptAlarm hotLoadingGroovyScriptAlarm() {
        // 默认打印告警信息到日志里
        return (scriptEntries, ex) -> log.error("scriptEntry load failed, scriptEntries info is : {}", scriptEntries, ex);
    }

    /**
     * spring容器助手
     */
    @Bean
    public ApplicationContextHelper applicationContextHelper() {

        return new ApplicationContextHelper();
    }

    /**
     * <p>
     * 导入CoreAutoConfiguration（springboot中默认的加载顺序是：先根据spring.factories文件读取到
     * ThingLinksGroovyEngineCoreAutoConfiguration类，然后处理里面的@Import 注解，所以ImportCoreAutoConfiguration里的bean
     * 会优先于ThingLinksGroovyEngineCoreAutoConfiguration所有的bean的注入）
     * </p>
     */
    @Import(value = {CoreAutoConfiguration.class})
    static class ImportCoreAutoConfiguration {

    }

}
