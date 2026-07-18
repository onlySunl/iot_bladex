

package org.springblade.modules.iot.convert;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.task.dto.RuleAction;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.controller.admin.rule.vo.TaskInfoSaveReqVo;
import org.springblade.modules.iot.controller.admin.rule.vo.TaskInfoVo;
import org.springblade.modules.iot.entity.TaskInfoDO;

import java.util.Collections;
import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/25 16:17
 * @Version: V1.0
 * @Description: 规则引擎转化
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface TaskInfoConvert {

    TaskInfoConvert INSTANCE = Mappers.getMapper(TaskInfoConvert.class);

    @Named("stringToRuleActionList")
    default List<RuleAction> stringToRuleActionList(String jsonString) {
        if (StrUtil.isBlank(jsonString)) {
            return Collections.emptyList();
        }
        return JsonUtils.parseArray(jsonString, RuleAction.class);
    }

    @Named("ruleActionList2string")
    default String ruleActionList2string(List<RuleAction> listeners) {
        if (CollectionUtil.isEmpty(listeners)) {
            return "[]";
        }
        return JsonUtils.toJsonString(listeners);
    }

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"stringToRuleActionList"})
    TaskInfo convertTask(TaskInfoDO selectById);

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"ruleActionList2string"})
    TaskInfoDO convertDO(TaskInfo taskInfo);

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"ruleActionList2string"})
    TaskInfoDO convert(TaskInfoSaveReqVo bo);

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"ruleActionList2string"})
    TaskInfoDO convert(TaskInfo bo);

    TaskInfo convert(TaskInfoVo vo);

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"stringToRuleActionList"})
    TaskInfo convert(TaskInfoDO tdo);

    @Mapping(source = "actions", target = "actions", qualifiedByName = {"stringToRuleActionList"})
    TaskInfoVo convertVo(TaskInfoDO tdo);

}
