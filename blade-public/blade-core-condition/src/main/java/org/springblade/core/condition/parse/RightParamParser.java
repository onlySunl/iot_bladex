package org.springblade.core.condition.parse;

import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * 条件右值解析器
 **/
public interface RightParamParser extends Ordered {

    /**
     * 解析器类型
     *
     * @return
     */
    String getType();

    /**
     * 解析值
     *
     * @param leftParamDTO 左值参数
     * @param rightParam   右值参数
     * @param data         上下文变量
     * @return
     */
    Object parseValue(SingleConditionDTO.LeftParamDTO leftParamDTO, SingleConditionDTO.RightParamDTO rightParam, Map<String, Object> data);
}
