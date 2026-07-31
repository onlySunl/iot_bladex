package org.springblade.core.condition.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 条件表达式描述DTO（集合子类全属性,用于Swagger描述，目前不支持描述多态）
 **/
@Getter
@Setter
public class ConditionInfoDTO implements Serializable {

    private static final long serialVersionUID = 8142872145936623926L;

    /**
     * 类型：GROUP | CONDITION
     */
    @NotNull
    protected BaseConditionDTO.ConditionExpTypeEnum type;

    /**
     * 逻辑操作符 AND|OR
     */
    protected BaseConditionDTO.LogicalOperator logicalOperator;

    /**
     * 条件表达式列表(type=GROUP时非空)
     */
    private List<ConditionInfoDTO> conditions;

    /**
     * 左值(type=CONDITION时非空)
     */
    private SingleConditionDTO.LeftParamDTO leftParam;

    /**
     * 操作符(type=CONDITION时非空)
     */
    private SingleConditionDTO.ConditionOperatorDTO operator;

    /**
     * 右值(type=CONDITION时非空)
     */
    private List<SingleConditionDTO.RightParamDTO> rightParams;

    /**
     * 是否过滤NULL值
     */
    private Boolean isFilterNull;

    private String uuid;
}
