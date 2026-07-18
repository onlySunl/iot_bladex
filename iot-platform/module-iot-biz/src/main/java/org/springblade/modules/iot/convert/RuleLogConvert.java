

package org.springblade.modules.iot.convert;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.rule.vo.RuleLogVo;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 规则日志
 */


@Mapper(builder = @Builder(disableBuilder = true))

public interface RuleLogConvert {
    RuleLogConvert INSTANCE = Mappers.getMapper(RuleLogConvert.class);


    PageResult<RuleLogVo> convertPage(PageResult<RuleLog> byRuleId);
}
