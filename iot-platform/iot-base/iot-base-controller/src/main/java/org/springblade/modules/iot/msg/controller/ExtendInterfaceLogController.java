package org.springblade.modules.iot.msg.controller;

import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.msg.entity.ExtendInterfaceLog;
import org.springblade.modules.iot.msg.service.ExtendInterfaceLogService;
import org.springblade.modules.iot.msg.vo.query.ExtendInterfaceLogPageQuery;
import org.springblade.modules.iot.msg.vo.result.ExtendInterfaceLogResultVO;
import org.springblade.modules.iot.msg.vo.save.ExtendInterfaceLogSaveVO;
import org.springblade.modules.iot.msg.vo.update.ExtendInterfaceLogUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口执行日志
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
@RequestMapping("/extendInterfaceLog")
@Tag(name = "接口执行日志")
public class ExtendInterfaceLogController extends SuperController<ExtendInterfaceLogService, Long, ExtendInterfaceLog, ExtendInterfaceLogSaveVO,
        ExtendInterfaceLogUpdateVO, ExtendInterfaceLogPageQuery, ExtendInterfaceLogResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<ExtendInterfaceLog> handlerWrapper(ExtendInterfaceLog model, PageParams<ExtendInterfaceLogPageQuery> params) {
        QueryWrap<ExtendInterfaceLog> queryWrap = super.handlerWrapper(model, params);
        Long tenantId = params.getModel().getTenantId();
        if (tenantId != null) {
            ContextUtil.setTenantBasePoolName(tenantId);
        }
        return queryWrap;
    }

}


