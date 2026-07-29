package org.springblade.modules.iot.base.controller.user;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.base.entity.user.BasePosition;
import org.springblade.modules.iot.base.service.user.BasePositionService;
import org.springblade.modules.iot.base.vo.query.user.BasePositionPageQuery;
import org.springblade.modules.iot.base.vo.result.user.BasePositionResultVO;
import org.springblade.modules.iot.base.vo.save.user.BasePositionSaveVO;
import org.springblade.modules.iot.base.vo.update.user.BasePositionUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 岗位
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basePosition")
@Tag(name = "岗位")
public class BasePositionController extends SuperController<BasePositionService, Long, BasePosition, BasePositionSaveVO, BasePositionUpdateVO, BasePositionPageQuery, BasePositionResultVO> {

    private final EchoService echoService;

    @Override
    public QueryWrap<BasePosition> handlerWrapper(BasePosition model, PageParams<BasePositionPageQuery> params) {
        QueryWrap<BasePosition> wrap = super.handlerWrapper(model, params);
        wrap.lambda().in(BasePosition::getOrgId, params.getModel().getOrgIdList());
        return wrap;
    }

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Parameters({
            @Parameter(name = "name", description = "name", required = true, schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "orgId", description = "orgId", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String name, @RequestParam Long orgId, @RequestParam(required = false) Long id) {
        return success(superService.check(name, orgId, id));
    }

}
