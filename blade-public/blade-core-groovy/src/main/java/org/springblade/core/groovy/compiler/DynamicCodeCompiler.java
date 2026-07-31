package org.springblade.core.groovy.compiler;

import org.springblade.core.groovy.entity.ScriptEntry;

/**
 * 动态代码编译器
 *
 * @author mqttsnet 2025/03/18 11:34
 */
public interface DynamicCodeCompiler {

    /**
     * 将code文本编译为Class对象
     *
     * @param code 代码文本
     * @param name 加载后该脚本在内存中的Class名称
     * @return java.lang.Class<?>
     * @throws Exception 运行时异常
     * @author mqttsnet 2024/9/18 11:35 上午
     */
    Class<?> compile(String code, String name) throws Exception;

    /**
     * 将scriptEngineEntry编译为Class
     *
     * @param scriptEntry 脚本引擎项
     * @return java.lang.Class<?>
     * @throws Exception 运行时异常
     * @author mqttsnet 2024/9/18 11:36 上午
     */
    Class<?> compile(ScriptEntry scriptEntry) throws Exception;
}
