package org.springblade.core.condition.model.dto;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.database.mybatis.conditions.Wrapper;

import java.util.List;
import java.util.Map;

/**
 * 条件输出参数
 **/
@Setter
@Getter
public class ConditionOutputParam {

    /**
     * 条件信息
     */
    private List<ConditionInfoDTO> conditionInfos;

    /**
     * Wrapper(目前是Wrapper方式在使用)
     */
    private Wrapper wrapper;

    /**
     * 条件右边的值（如果有配置变量需要传递）
     */
    private Map<String, Object> rightData;

    /**
     * 查询的dto full name (目前主要是Sql方式使用，Schema中没有，需要知道是查询的哪个表)
     */
    private String dtoFullName;

    /**
     * 查询得到dto对应App name
     */
    private String appName;

    /**
     * 右值表达式变量，key为uuid , value为表达式值
     */
    private Map<String, List<Map<String, Object>>> expressionValues;

    /**
     * 是否使用实体SimpleName作为条件左值
     */
    private boolean useEntitySimpleName;

    /**
     * 是否使用枚举code作为条件右值
     */
    private boolean useEnumCodeRightValue;

    public static ConditionOutputParam from(ParamMapBo paramMapBo) {
        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(paramMapBo.getConditionJsonStr(), ConditionInfoDTO.class);
        ConditionOutputParam param = new ConditionOutputParam();
        param.setConditionInfos(conditionInfos);
        param.setRightData(paramMapBo.getRightData());
        return param;
    }
}
