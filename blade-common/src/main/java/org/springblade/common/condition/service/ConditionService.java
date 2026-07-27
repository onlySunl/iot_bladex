package org.springblade.common.condition.service;
import org.springblade.common.condition.model.dto.ConditionInfoDTO;
import org.springblade.common.condition.model.dto.ConditionParamResult;
public interface ConditionService {
    ConditionParamResult evaluate(ConditionInfoDTO condition);
}
