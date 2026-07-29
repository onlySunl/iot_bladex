package org.springblade.modules.iot.system.controller.anyone;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import com.mqttsnet.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.common.constant.AppendixType;
import org.springblade.modules.iot.file.service.AppendixService;
import org.springblade.modules.iot.system.entity.application.DefApplication;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.service.application.DefApplicationService;
import org.springblade.modules.iot.system.service.tenant.DefTenantService;
import org.springblade.modules.iot.system.vo.query.tenant.DefTenantPageQuery;
import org.springblade.modules.iot.system.vo.result.application.DefApplicationResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefTenantResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mqttsnet
 * @date 2021/10/26 21:40
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anyone")
@Tag(name = "需要登录但无需验证uri权限的接口")
public class TenantAnyoneController {

    private final DefApplicationService defApplicationService;
    private final EchoService echoService;
    private final DefTenantService defTenantService;
    private final AppendixService appendixService;


    @Operation(summary = "设置我的默认应用")
    @PostMapping("/updateDefApp")
    @WebLog(value = "设置我的默认应用")
    public R<Boolean> updateDefApp(@RequestParam Long applicationId) {
        return R.success(defApplicationService.updateDefApp(applicationId, ContextUtil.getUserId()));
    }

    @Operation(summary = "查询我的默认应用")
    @GetMapping("/getDefApp")
    @WebLog(value = "查询我的默认应用")
    public R<DefApplication> getDefApp() {
        return R.success(defApplicationService.getDefApp(ContextUtil.getUserId()));
    }

    @Operation(summary = "查询我的应用")
    @GetMapping("/findMyApplication")
    @WebLog(value = "查询我的应用")
    public R<List<DefApplicationResultVO>> findMyApplication(
            @RequestParam(required = false) Long tenantId, @RequestParam(required = false) String name) {
        List<DefApplicationResultVO> list = defApplicationService.findMyApplication(tenantId, name);
        echoService.action(list);
        appendixService.echoAppendix(list, AppendixType.System.DEF__APPLICATION__LOGO);
        return R.success(list);
    }

    @Operation(summary = "查询推荐应用")
    @GetMapping("/findRecommendApplication")
    @WebLog(value = "查询推荐应用")
    public R<List<DefApplicationResultVO>> findRecommendApplication(
            @RequestParam(required = false) Long tenantId, @RequestParam(required = false) String name) {
        List<DefApplicationResultVO> list = defApplicationService.findRecommendApplication(tenantId, name);
        echoService.action(list);
        appendixService.echoAppendix(list, AppendixType.System.DEF__APPLICATION__LOGO);
        return R.success(list);
    }

    @Operation(summary = "分页查询我申请的企业信息", description = "分页查询我申请的企业信息")
    @PostMapping("/myTenantPage")
    @WebLog(value = "'分页查询我申请的企业信息:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<DefTenantResultVO>> myTenantPage(@RequestBody @Validated PageParams<DefTenantPageQuery> params) {
        IPage<DefTenant> page = params.buildPage();
        LbQueryWrap<DefTenant> wrap = Wraps.<DefTenant>lbQ().eq(DefTenant::getCreatedBy, ContextUtil.getUserId());
        defTenantService.page(page, wrap);
        IPage<DefTenantResultVO> voPage = BeanPlusUtil.toBeanPage(page, DefTenantResultVO.class);
        echoService.action(voPage);
        return R.success(voPage);
    }

}
