package org.springblade.core.groovy.executor;

import org.springblade.core.groovy.entity.EngineExecutorResult;
import org.springblade.core.groovy.entity.ExecuteParams;
import org.springblade.core.groovy.entity.ScriptEntry;
import org.springblade.core.groovy.entity.ScriptQuery;
import org.springframework.lang.NonNull;

/**
 * 引擎执行器
 *
 * @author mqttsnet 2025/03/18 12:25
 */
public interface EngineExecutor {

    /**
     * 执行脚本
     *
     * @param scriptQuery   执行条件
     * @param executeParams 业务参数（传递到groovy脚本里的参数都可以放这里面）
     * @return {@link EngineExecutorResult} 执行结果
     * @author mqttsnet 2024/9/18 12:57 下午
     */
    @NonNull
    EngineExecutorResult execute(@NonNull ScriptQuery scriptQuery, ExecuteParams executeParams);

    /**
     * 执行脚本
     *
     * @param scriptEntry   脚本实体
     * @param executeParams 业务参数（传递到groovy脚本里的参数都可以放这里面）
     * @return {@link EngineExecutorResult} 执行结果
     * @author mqttsnet 2024/9/18 12:57 下午
     */
    @NonNull
    EngineExecutorResult execute(@NonNull ScriptEntry scriptEntry, ExecuteParams executeParams);

    /**
     * 根据groovy里的方法名来执行脚本方法
     *
     * @param groovyMethodName 方法名称
     * @param scriptQuery      查询参数
     * @param executeParams    参数
     * @return {@link EngineExecutorResult} 执行结果
     * @author mqttsnet 2024/9/30 10:56 下午
     */
    @NonNull
    EngineExecutorResult execute(@NonNull String groovyMethodName,
                                 @NonNull ScriptQuery scriptQuery,
                                 ExecuteParams executeParams);

    /**
     * 根据groovy里的方法名来执行脚本方法
     *
     * @param groovyMethodName 方法名称
     * @param scriptEntry      脚本实体
     * @param executeParams    参数
     * @return {@link EngineExecutorResult} 执行结果
     * @author mqttsnet 2024/9/30 10:56 下午
     */
    @NonNull
    EngineExecutorResult execute(@NonNull String groovyMethodName,
                                 @NonNull ScriptEntry scriptEntry,
                                 @NonNull ExecuteParams executeParams);

}
