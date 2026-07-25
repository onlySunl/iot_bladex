

package org.springblade.modules.iot.api.rule;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.service.rule.IEiotRuleInfoService;
import org.springframework.stereotype.Service;

@Service
public class RuleInfoApiImpl implements RuleInfoApi {

    @Resource
    private IEiotRuleInfoService ruleInfoService;

    @Override
    public PageResult<RuleInfo> selectPage(RuleInfoPageReqVO reqVO) {
        return ruleInfoService.getRuleInfoPage(reqVO);
    }

}
