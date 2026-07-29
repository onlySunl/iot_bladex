package org.springblade.modules.iot.base.controller.common;

import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.base.entity.common.BaseParameter;
import org.springblade.modules.iot.base.service.common.BaseParameterService;
import org.springblade.modules.iot.base.vo.query.common.BaseParameterPageQuery;
import org.springblade.modules.iot.base.vo.result.common.BaseParameterResultVO;
import org.springblade.modules.iot.base.vo.save.common.BaseParameterSaveVO;
import org.springblade.modules.iot.base.vo.update.common.BaseParameterUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 个性参数
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-08
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseParameter")
@Tag(name = "个性参数")
public class BaseParameterController extends SuperController<BaseParameterService, Long, BaseParameter, BaseParameterSaveVO, BaseParameterUpdateVO, BaseParameterPageQuery, BaseParameterResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}
