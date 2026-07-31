package org.springblade.core.condition.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Condition Map 参数对象
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "hiddenBuilder")
public class ParamMapBo {

    /**
     * 条件 JSON 信息
     */
    private final String conditionJsonStr;

    /**
     * 右值信息
     */
    private final Map<String, Object> rightData;

}
