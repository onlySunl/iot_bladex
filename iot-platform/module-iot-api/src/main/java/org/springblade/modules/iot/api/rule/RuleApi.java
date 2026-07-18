

package org.springblade.modules.iot.api.rule;

import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;

public interface RuleApi {

    PageResult<RuleInfo> selectPage(RuleInfoPageReqVO reqVO);

}
