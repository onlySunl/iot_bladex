package org.springblade.modules.iot.system.controller.system;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.system.entity.system.DefDict;
import org.springblade.modules.iot.system.service.system.DefDictItemService;
import org.springblade.modules.iot.system.vo.query.system.DefDictItemPageQuery;
import org.springblade.modules.iot.system.vo.result.system.DefDictItemResultVO;
import org.springblade.modules.iot.system.vo.save.system.DefDictItemSaveVO;
import org.springblade.modules.iot.system.vo.update.system.DefDictItemUpdateVO;
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

import static org.springblade.modules.iot.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static org.springblade.modules.iot.common.constant.SwaggerConstants.DATA_TYPE_STRING;


/**
 * <p>
 * 前端控制器
 * 字典项
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-04
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defDictItem")
@Tag(name = "字典项")
public class DefDictItemController extends SuperController<DefDictItemService, Long, DefDict,
        DefDictItemSaveVO, DefDictItemUpdateVO, DefDictItemPageQuery, DefDictItemResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public void handlerQueryParams(PageParams<DefDictItemPageQuery> params) {
        ArgumentAssert.notNull(params.getModel().getParentId(), "请选择字典");
    }

    @Override
    public QueryWrap<DefDict> handlerWrapper(DefDict model, PageParams<DefDictItemPageQuery> params) {
        QueryWrap<DefDict> wrap = super.handlerWrapper(null, params);
        LbQueryWrap<DefDict> wrapper = wrap.lambda();
        wrapper.eq(DefDict::getParentId, model.getParentId())
                .like(DefDict::getKey, model.getKey())
                .like(DefDict::getName, model.getName())
                .in(DefDict::getClassify, params.getModel().getClassify())
                .in(DefDict::getState, params.getModel().getState());
        return wrap;
    }

    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "dictId", description = "字典ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "key", description = "字典标识", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测字典项标识是否可用", description = "检测字典项标识是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String key, @RequestParam Long dictId, @RequestParam(required = false) Long id) {
        return success(superService.checkItemByKey(key, dictId, id));
    }

}
