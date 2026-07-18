

package org.springblade.modules.iot.api.rule;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.service.rule.EiotRuleInfoService;
import org.springframework.stereotype.Service;

@Service
public class RuleApiImpl implements RuleApi {

    @Resource
    private EiotRuleInfoService ruleInfoService;

    @Override
    public PageResult<RuleInfo> selectPage(RuleInfoPageReqVO reqVO) {
        return ruleInfoService.getRuleInfoPage(reqVO);
    }

}
