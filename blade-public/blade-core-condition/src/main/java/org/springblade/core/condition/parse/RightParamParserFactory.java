package org.springblade.core.condition.parse;

import org.springblade.basic.exception.BizException;
import org.springblade.core.condition.enumeration.ParamTypeEnum;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 右值解析器工程
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RightParamParserFactory {

    /**
     * type -> parser
     */
    private Map<String, RightParamParser> parsers = new ConcurrentHashMap<>();

    public RightParamParserFactory(Map<String, RightParamParser> parsersMap) {
        parsers.putAll(parsersMap);
    }

    public Object getValue(SingleConditionDTO.LeftParamDTO leftParamDTO,
                           SingleConditionDTO.RightParamDTO rightParam,
                           Map<String, Object> data) {
        ParamTypeEnum type = rightParam.getType();
        RightParamParser parser = parsers.get(type.name());

        if (Objects.isNull(parser)) {
            log.warn("没有该类型[{}]的右值解析器, 如果需要请自定义实现类", type);
            throw new BizException("没有该类型的右值解析器: " + type);
        }
        return parser.parseValue(leftParamDTO, rightParam, data);
    }
}
