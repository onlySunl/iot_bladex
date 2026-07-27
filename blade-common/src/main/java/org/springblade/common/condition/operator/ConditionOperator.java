package org.springblade.common.condition.operator;
import org.springblade.common.condition.model.dto.ConditionInfoDTO;
import org.springblade.common.condition.model.dto.ConditionParamResult;
public interface ConditionOperator {
    ConditionParamResult build(ConditionInfoDTO condition);
}
