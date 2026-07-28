package org.springblade.modules.iot.linkage.controller;

import com.mqttsnet.basic.interfaces.echo.EchoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
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
public class RuleConditionActionController extends BladeController {
    private final EchoService echoService;


    public EchoService getEchoService() {
        return echoService;
    }

}


