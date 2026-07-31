package org.springblade.core.groovy.engine;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.springblade.core.groovy.config.GroovyProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Groovy 脚本引擎
 *
 * @author Chill
 */
@Slf4j
@Component
public class GroovyScriptEngine {

    private final GroovyProperties properties;
    private final GroovyShell groovyShell;
    private final Map<String, Script> scriptCache;

    public GroovyScriptEngine(GroovyProperties properties) {
        this.properties = properties;
        this.scriptCache = new ConcurrentHashMap<>();
        this.groovyShell = createGroovyShell();
    }

    /**
     * 创建 GroovyShell
     */
    private GroovyShell createGroovyShell() {
        CompilerConfiguration config = new CompilerConfiguration();
        
        // 添加默认导入
        ImportCustomizer importCustomizer = new ImportCustomizer();
        importCustomizer.addImports("java.util.*");
        importCustomizer.addImports("java.math.*");
        importCustomizer.addStarImports("groovy.json");
        config.addCompilationCustomizers(importCustomizer);
        
        return new GroovyShell(config);
    }

    /**
     * 执行脚本
     *
     * @param scriptText 脚本内容
     * @param variables  变量
     * @return 执行结果
     */
    public Object execute(String scriptText, Map<String, Object> variables) {
        try {
            Binding binding = new Binding();
            if (variables != null) {
                variables.forEach(binding::setVariable);
            }
            
            Script script;
            if (properties.getCacheEnabled()) {
                String key = String.valueOf(scriptText.hashCode());
                script = scriptCache.computeIfAbsent(key, k -> groovyShell.parse(scriptText));
            } else {
                script = groovyShell.parse(scriptText);
            }
            
            script.setBinding(binding);
            return script.run();
        } catch (Exception e) {
            log.error("Groovy 脚本执行失败", e);
            throw new RuntimeException("Groovy 脚本执行失败: " + e.getMessage(), e);
        }
    }

    /**
     * 执行脚本（无变量）
     *
     * @param scriptText 脚本内容
     * @return 执行结果
     */
    public Object execute(String scriptText) {
        return execute(scriptText, null);
    }

    /**
     * 编译脚本
     *
     * @param scriptText 脚本内容
     * @return 编译后的脚本
     */
    public Script compile(String scriptText) {
        return groovyShell.parse(scriptText);
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        scriptCache.clear();
        log.info("Groovy 脚本缓存已清除");
    }

}
