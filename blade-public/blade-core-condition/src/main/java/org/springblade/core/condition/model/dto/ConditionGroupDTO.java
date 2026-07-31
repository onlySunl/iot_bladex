package org.springblade.core.condition.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 条件表达式分组
 **/
@Setter
@Getter
public class ConditionGroupDTO extends BaseConditionDTO {

    private static final long serialVersionUID = 5335683460184764572L;

    /**
     * 条件表达式列表
     */
    @NotNull
    private List<BaseConditionDTO> conditions;
}

