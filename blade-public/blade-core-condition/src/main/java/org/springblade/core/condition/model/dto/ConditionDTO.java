package org.springblade.core.condition.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 条件表达式保存DTO
 **/
@Getter
@Setter
public class ConditionDTO {

    private static final long serialVersionUID = 7670584109994266629L;

    @NotNull
    private List<ConditionInfoDTO> conditionInfos;
}
