package org.springblade.common.condition.model.dto;
import lombok.Data;
import java.util.List;
@Data
public class ConditionInfoDTO {
    private String logic;
    private List<BaseConditionDTO> conditions;
}
