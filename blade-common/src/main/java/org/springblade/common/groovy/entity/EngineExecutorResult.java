package org.springblade.common.groovy.entity;
import lombok.Data;
import org.springblade.common.groovy.constants.ExecutionStatus;
@Data
public class EngineExecutorResult {
    private ExecutionStatus status;
    private Object result;
    private String error;
}
