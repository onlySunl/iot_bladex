package org.springblade.modules.iot.system.controller.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.annotation.user.LoginUser;
import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperReadController;
import com.mqttsnet.basic.base.entity.SuperEntity;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.model.entity.system.SysUser;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRel;
import org.springblade.modules.iot.system.service.application.DefTenantApplicationRelService;
import org.springblade.modules.iot.system.vo.query.application.DefTenantApplicationRelPageQuery;
import org.springblade.modules.iot.system.vo.result.application.DefTenantApplicationRelResultVO;
import org.springblade.modules.iot.system.vo.save.application.DefTenantApplicationRelSaveVO;
import org.springblade.modules.iot.system.vo.update.application.DefTenantApplicationRelUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * 租户应用授权
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defTenantApplicationRel")
@Tag(name = "租户应用授权")
public class DefTenantApplicationRelController extends SuperReadController
        <DefTenantApplicationRelService, Long, DefTenantApplicationRel, DefTenantApplicationRelPageQuery, DefTenantApplicationRelResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public void handlerResult(IPage<DefTenantApplicationRelResultVO> page) {
        super.handlerResult(page);
        // true 表示已过期
        page.getRecords().forEach(item ->
                item.setExpired(item.getExpirationTime() != null && item.getExpirationTime().isBefore(LocalDateTime.now()))
        );
    }

    @Override
    public R<DefTenantApplicationRelResultVO> getDetail(@RequestParam("id") Long id) {
        return R.success(superService.getDetailById(id));
    }

    @Operation(summary = "授权")
    @PostMapping(value = "/grant")
    @WebLog(value = "授权")
    public R<Boolean> grant(@RequestBody @Validated DefTenantApplicationRelSaveVO saveVO, @Parameter(hidden = true) @LoginUser(isUser = true) SysUser sysUser) {
        return success(superService.grant(saveVO, sysUser));
    }

    @Operation(summary = "取消授权")
    @PostMapping(value = "/cancel")
    @WebLog(value = "取消授权")
    public R<Boolean> cancel(@RequestBody List<Long> ids, @Parameter(hidden = true) @LoginUser(isUser = true) SysUser sysUser) {
        return success(superService.cancel(ids, sysUser));
    }


    @Operation(summary = "续期")
    @PostMapping(value = "/renewal")
    @WebLog(value = "续期")
    public R<Boolean> renewal(@RequestBody @Validated(SuperEntity.Update.class) DefTenantApplicationRelUpdateVO updateVO, @Parameter(hidden = true) @LoginUser(isUser = true) SysUser sysUser) {
        return success(superService.renewal(updateVO, sysUser));
    }


}
