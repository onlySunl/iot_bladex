

package org.springblade.modules.iot.convert;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.rule.dto.FilterConfig;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.TriggerOptions;
import org.springblade.modules.iot.api.task.dto.RuleAction;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.controller.admin.rule.vo.EiotRuleInfoSaveReqVO;
import org.springblade.modules.iot.entity.EiotRuleInfoDO;

import java.util.Collections;
import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/25 16:17
 * @Version: V1.0
 * @Description: 规则引擎转化
 */
@Mapper(builder = @Builder(disableBuilder = true))

public interface RuleInfoConvert {

    RuleInfoConvert INSTANCE = Mappers.getMapper(RuleInfoConvert.class);

    @Mappings({
            @Mapping(source = "listeners", target = "listeners", qualifiedByName = {"stringToFilterConfigList"}),
            @Mapping(source = "actions", target = "actions", qualifiedByName = {"stringToRuleActionList"}),
//            @Mapping(source = "actions", target = "actions", ignore = true),
            @Mapping(source = "filters", target = "filters", qualifiedByName = {"stringToFilterConfigList"}),
            @Mapping(source = "triggerOptions", target = "triggerOptions", qualifiedByName = {"stringToTriggerOptions"})
    })
    RuleInfo convert(EiotRuleInfoDO ylRuleInfoDO);

    PageResult<RuleInfo> convertPage(PageResult<EiotRuleInfoDO> selectPage);

    @Named("stringToFilterConfigList")
    default List<FilterConfig> stringToFilterConfigList(String jsonString) {
        if (StrUtil.isBlank(jsonString)) {
            return Collections.emptyList();
        }
        return JsonUtils.parseArray(jsonString, FilterConfig.class);
    }

    @Named("stringToRuleActionList")
    default List<RuleAction> stringToRuleActionList(String jsonString) {
        if (StrUtil.isBlank(jsonString)) {
            return Collections.emptyList();
        }
        return JsonUtils.parseArray(jsonString, RuleAction.class);
    }

    @Named("stringToTriggerOptions")
    default TriggerOptions stringToTriggerOptions(String jsonString) {
        if (StrUtil.isBlank(jsonString)) {
            return new TriggerOptions();
        }
        return JsonUtils.parseObject(jsonString, TriggerOptions.class);
    }


    RuleInfo edit2Info(EiotRuleInfoSaveReqVO createReqVO);

    @Mappings({
            @Mapping(source = "listeners", target = "listeners", qualifiedByName = {"filterConfigList2string"}),
            @Mapping(source = "actions", target = "actions", qualifiedByName = {"ruleActionList2string"}),
            @Mapping(source = "filters", target = "filters", qualifiedByName = {"filterConfigList2string"}),
            @Mapping(source = "triggerOptions", target = "triggerOptions", qualifiedByName = {"triggerOptions2string"})
    })
    EiotRuleInfoDO toDo(RuleInfo ruleInfo);

    @Named("filterConfigList2string")
    default String filterConfigList2string(List<FilterConfig> listeners) {
        if (CollectionUtil.isEmpty(listeners)) {
            return "[]";
        }
        return JsonUtils.toJsonString(listeners);
    }

    @Named("ruleActionList2string")
    default String ruleActionList2string(List<RuleAction> listeners) {
        if (CollectionUtil.isEmpty(listeners)) {
            return "[]";
        }
        return JsonUtils.toJsonString(listeners);
    }

    @Named("triggerOptions2string")
    default String triggerOptions2string(TriggerOptions triggerOptions) {
        if (triggerOptions == null) {
            return "{}";
        }
        return JsonUtils.toJsonString(triggerOptions);
    }

}
