package org.springblade.common.groovy.executor;
import org.springblade.common.groovy.entity.EngineExecutorResult;
import org.springblade.common.groovy.entity.ExecuteParams;
public interface EngineExecutor {
    EngineExecutorResult execute(ExecuteParams params);
}
