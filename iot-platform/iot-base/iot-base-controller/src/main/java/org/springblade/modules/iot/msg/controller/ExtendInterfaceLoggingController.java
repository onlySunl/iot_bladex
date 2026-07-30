package org.springblade.modules.iot.msg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.modules.iot.msg.entity.ExtendInterfaceLogging;
import org.springblade.modules.iot.msg.service.ExtendInterfaceLoggingService;
import org.springblade.modules.iot.msg.vo.query.ExtendInterfaceLoggingPageQuery;
import org.springblade.modules.iot.msg.vo.result.ExtendInterfaceLoggingResultVO;
import org.springblade.modules.iot.msg.vo.save.ExtendInterfaceLoggingSaveVO;
import org.springblade.modules.iot.msg.vo.update.ExtendInterfaceLoggingUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口执行日志记录
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendInterfaceLogging")
@Tag(name = "接口执行日志记录")
public class ExtendInterfaceLoggingController extends SuperController<ExtendInterfaceLoggingService, Long, ExtendInterfaceLogging, ExtendInterfaceLoggingSaveVO,
        ExtendInterfaceLoggingUpdateVO, ExtendInterfaceLoggingPageQuery, ExtendInterfaceLoggingResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public IPage<ExtendInterfaceLogging> query(PageParams<ExtendInterfaceLoggingPageQuery> params) {
        Long tenantId = params.getModel().getTenantId();
        if (tenantId != null) {
            ContextUtil.setTenantBasePoolName(tenantId);
        }
        return super.query(params);
    }
}


