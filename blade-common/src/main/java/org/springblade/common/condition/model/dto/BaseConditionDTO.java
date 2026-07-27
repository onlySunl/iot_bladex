package org.springblade.common.condition.model.dto;
import lombok.Data;
@Data
public class BaseConditionDTO {
    private String field;
    private String operator;
    private Object value;
}
