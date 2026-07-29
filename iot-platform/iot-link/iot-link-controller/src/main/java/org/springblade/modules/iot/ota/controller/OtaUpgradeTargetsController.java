package org.springblade.modules.iot.ota.controller;

import org.springblade.basic.base.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTargetsResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTargetsSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeTargetsUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * OTA升级目标
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/otaUpgradeTargets")
@Tag(name = "OTA升级目标")
public class OtaUpgradeTargetsController extends SuperController<OtaUpgradeTargetsService, Long, OtaUpgradeTargets
        , OtaUpgradeTargetsSaveVO, OtaUpgradeTargetsUpdateVO, OtaUpgradeTargetsPageQuery, OtaUpgradeTargetsResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<OtaUpgradeTargets> handlerWrapper(OtaUpgradeTargets model, PageParams<OtaUpgradeTargetsPageQuery> params) {
        QueryWrap<OtaUpgradeTargets> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("ota_upgrade_targets");
        return queryWrap;
    }


}


