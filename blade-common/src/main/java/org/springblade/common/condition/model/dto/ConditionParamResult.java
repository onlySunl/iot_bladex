package org.springblade.common.condition.model.dto;
import lombok.Data;
@Data
public class ConditionParamResult {
    private String sql;
    private Object[] params;
}
