package org.springblade.core.condition.parse;

import org.springblade.core.condition.convertor.RightParamValueConvertor;
import org.springblade.core.condition.enumeration.ParamTypeEnum;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 常量实现类
 **/
@Slf4j
@Component
public class ConstantRightParamParser extends AbstractRightParamParser {

    @Autowired
    private RightParamValueConvertor valueConvertor;

    @Override
    public String getType() {
        return ParamTypeEnum.CONSTANT.name();
    }

    @Override
    public Object parseValue(SingleConditionDTO.LeftParamDTO leftParamDTO,
                             SingleConditionDTO.RightParamDTO rightParam,
                             Map<String, Object> data) {
        return valueConvertor.convert(leftParamDTO.getDataType(), rightParam.getValue());
    }
}
