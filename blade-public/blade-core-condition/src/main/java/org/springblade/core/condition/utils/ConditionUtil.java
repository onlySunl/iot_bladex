package org.springblade.core.condition.utils;

import com.google.common.collect.Lists;
import org.springblade.core.condition.model.dto.BaseConditionDTO;
import org.springblade.core.condition.model.dto.ConditionGroupDTO;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public final class ConditionUtil {

    private ConditionUtil() {
    }

    public static List<BaseConditionDTO> getBaseConditionDTOs(List<ConditionInfoDTO> dtos) {
        return getConditions(dtos);
    }


    private static List<BaseConditionDTO> getConditions(List<ConditionInfoDTO> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        List<BaseConditionDTO> conditions = Lists.newArrayListWithExpectedSize(dtos.size());
        dtos.forEach(dto -> {
            switch (dto.getType()) {
                case GROUP:
                    ConditionGroupDTO groupDTO = new ConditionGroupDTO();
                    groupDTO.setType(dto.getType());
                    groupDTO.setLogicalOperator(dto.getLogicalOperator());
                    groupDTO.setUuid(dto.getUuid());
                    groupDTO.setConditions(getConditions(dto.getConditions()));
                    conditions.add(groupDTO);
                    break;
                case CONDITION:
                    SingleConditionDTO singleConditionDTO = new SingleConditionDTO();
                    singleConditionDTO.setType(dto.getType());
                    singleConditionDTO.setLogicalOperator(dto.getLogicalOperator());
                    singleConditionDTO.setUuid(dto.getUuid());
                    singleConditionDTO.setLeftParam(dto.getLeftParam());
                    singleConditionDTO.setOperator(dto.getOperator());
                    singleConditionDTO.setRightParams(dto.getRightParams());
                    conditions.add(singleConditionDTO);
                default:
            }
        });
        return conditions;
    }

}
