package org.springblade.modules.iot.system.controller.application;

import com.mqttsnet.basic.base.controller.SuperReadController;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRecord;
import org.springblade.modules.iot.system.service.application.DefTenantApplicationRecordService;
import org.springblade.modules.iot.system.vo.query.application.DefTenantApplicationRecordPageQuery;
import org.springblade.modules.iot.system.vo.result.application.DefTenantApplicationRecordResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 租户应用授权记录
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defTenantApplicationRecord")
@Tag(name = "租户应用授权记录")
public class DefTenantApplicationRecordController extends SuperReadController
        <DefTenantApplicationRecordService, Long, DefTenantApplicationRecord, DefTenantApplicationRecordPageQuery, DefTenantApplicationRecordResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}

