package org.springblade.modules.iot.system.controller.tenant;

import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperCacheController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.service.tenant.DefTenantService;
import org.springblade.modules.iot.system.vo.query.tenant.DefTenantPageQuery;
import org.springblade.modules.iot.system.vo.result.tenant.DefTenantResultVO;
import org.springblade.modules.iot.system.vo.save.tenant.DefTenantSaveVO;
import org.springblade.modules.iot.system.vo.update.tenant.DefTenantUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 企业
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-13
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/anyone/defTenant")
@Tag(name = "我的企业")
public class DefTenantAnyoneController extends SuperCacheController<DefTenantService, Long, DefTenant,
        DefTenantSaveVO, DefTenantUpdateVO, DefTenantPageQuery, DefTenantResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Operation(summary = "修改租户审核状态", description = "修改租户审核状态")
    @PostMapping("/updateStatus")
    @WebLog("修改租户审核状态")
    public R<Boolean> updateStatus(@NotNull(message = "请修改正确的企业") @RequestParam Long id,
                                   @RequestParam @NotNull(message = "请传递状态值") String status,
                                   @RequestParam(required = false) String reviewComments) {
        return success(superService.updateStatus(id, status, reviewComments));
    }

    @Override
    public QueryWrap<DefTenant> handlerWrapper(DefTenant model, PageParams<DefTenantPageQuery> params) {
        QueryWrap<DefTenant> wrap = super.handlerWrapper(model, params);
        wrap.lambda().eq(DefTenant::getCreatedBy, ContextUtil.getUserId());
        return wrap;
    }

    @Override
    public R<DefTenant> save(@RequestBody @Validated DefTenantSaveVO defTenantSaveVO) {
        return R.success(superService.register(defTenantSaveVO));
    }
}
