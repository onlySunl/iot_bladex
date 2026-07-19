package org.springblade.modules.iot.api.rule;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 规则引擎对外API控制器
 * 与 {@link org.springblade.modules.iot.api.rule.service.RemoteIotRuleService} 一一对应
 */
@RestController
@RequestMapping("/ruleInfoApi")
@Tag(name = "规则引擎API", description = "规则分页查询接口")
public class RuleInfoApiController extends BladeController {

    @Resource
    private RuleInfoApi ruleApi;

    @PostMapping("/selectPage")
    @Operation(summary = "分页查询规则列表")
    public PageResult<RuleInfo> selectPage(@RequestBody RuleInfoPageReqVO reqVO) {
        return ruleApi.selectPage(reqVO);
    }
}