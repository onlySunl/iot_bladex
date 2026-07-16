
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.convert;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.common.util.json.JsonUtils;
import org.springblade.modules.iot.api.task.dto.RuleAction;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.controller.admin.rule.vo.TaskInfoSaveReqVo;
import org.springblade.modules.iot.controller.admin.rule.vo.TaskInfoVo;
import org.springblade.modules.iot.entity.TaskInfoDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

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
