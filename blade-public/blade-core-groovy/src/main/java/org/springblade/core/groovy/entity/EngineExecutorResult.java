package org.springblade.core.groovy.entity;

import org.springblade.core.groovy.constants.ExecutionStatus;
import lombok.Data;

/**
 * 脚本执行结果
 *
 * @author mqttsnet 2025/03/18 12:44
 */
@Data
public class EngineExecutorResult {

    /**
     * 执行状态
     */
    private ExecutionStatus executionStatus;

    /**
     * 返回内容
     */
    private Object context;

    /**
     * 异常信息
     */
    private Throwable exception;

    /**
     * 自定义异常描述
     */
    private String errorMessage;

    private EngineExecutorResult(ExecutionStatus executionStatus, String errorMessage) {
        this.executionStatus = executionStatus;
        this.errorMessage = errorMessage;
    }

    private EngineExecutorResult(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    private EngineExecutorResult(ExecutionStatus executionStatus, Throwable exception) {
        this.executionStatus = executionStatus;
        this.exception = exception;
    }

    private EngineExecutorResult(ExecutionStatus executionStatus, Object context) {
        this.executionStatus = executionStatus;
        this.context = context;
    }

    /**
     * 执行失败
     *
     * @param exception 异常信息
     * @return org.springblade.core.groovy.entity.EngineExecutorResult<java.lang.Object>
     * @author mqttsnet 2024/9/18 12:54 下午
     */
    public static EngineExecutorResult failed(Throwable exception) {
        return new EngineExecutorResult(ExecutionStatus.FAILED, exception);
    }

    /**
     * 执行失败
     *
     * @param errorMessage 异常信息
     * @return org.springblade.core.groovy.entity.EngineExecutorResult<java.lang.Object>
     * @author mqttsnet 2024/9/18 12:54 下午
     */
    public static EngineExecutorResult failed(String errorMessage) {
        return new EngineExecutorResult(ExecutionStatus.PARAM_ERROR, errorMessage);
    }

    /**
     * 执行成功
     *
     * @param context 内容
     * @return org.springblade.core.groovy.entity.EngineExecutorResult<java.lang.Object>
     * @author mqttsnet 2024/9/18 12:55 下午
     */
    public static EngineExecutorResult success(Object context) {
        return success(ExecutionStatus.SUCCESS, context);
    }

    /**
     * 执行成功
     *
     * @param context 内容
     * @param status  执行状态
     * @return org.springblade.core.groovy.entity.EngineExecutorResult<java.lang.Object>
     * @author mqttsnet 2024/9/18 12:55 下午
     */
    public static EngineExecutorResult success(ExecutionStatus status, Object context) {
        return new EngineExecutorResult(status, context);
    }

    /**
     * 获取context为指定的类型
     */
    public <T> T context() {
        return (T) context;
    }
}
