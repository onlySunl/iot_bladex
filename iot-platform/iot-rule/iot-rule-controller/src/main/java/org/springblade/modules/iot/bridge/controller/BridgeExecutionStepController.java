package org.springblade.modules.iot.bridge.controller;

import com.mqttsnet.basic.interfaces.echo.EchoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.service.bridge.BridgeExecutionStepService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 桥接执行步骤明细控制器(主要给监控告警按状态查异常步骤)。
 * 常规链路回放走 BridgeExecutionTraceController.getDetail() 附带的 steps 子集合。
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/bridgeExecutionStep")
@Tag(name = "桥接执行步骤明细")
public class BridgeExecutionStepController extends BladeController {

    private final EchoService echoService;

    public EchoService getEchoService() {
        return echoService;
    }

    private final BridgeExecutionStepService bridgeExecutionStepService;

}