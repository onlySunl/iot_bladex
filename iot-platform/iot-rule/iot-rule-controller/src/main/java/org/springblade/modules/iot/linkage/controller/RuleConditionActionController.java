package org.springblade.modules.iot.linkage.controller;

import org.springblade.core.mp.base.BaseController;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.entity.linkage.RuleConditionAction;
import org.springblade.modules.iot.service.linkage.RuleConditionActionService;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionActionPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleConditionActionResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleConditionActionSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleConditionActionUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 规则条件动作表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:24:37
 * @create [2023-07-19 23:24:37] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/ruleConditionAction")
@Tag(name = "规则条件动作")
public class RuleConditionActionController extends BaseController<RuleConditionActionService, Long, RuleConditionAction, RuleConditionActionSaveVO,
        RuleConditionActionUpdateVO, RuleConditionActionPageQuery, RuleConditionActionResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


