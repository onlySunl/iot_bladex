package org.springblade.modules.iot.base.controller.common;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.base.biz.common.BaseDictBiz;
import org.springblade.modules.iot.base.entity.common.BaseDict;
import org.springblade.modules.iot.base.service.common.BaseDictService;
import org.springblade.modules.iot.base.vo.query.common.BaseDictPageQuery;
import org.springblade.modules.iot.base.vo.result.common.BaseDictResultVO;
import org.springblade.modules.iot.base.vo.save.common.BaseDictSaveVO;
import org.springblade.modules.iot.base.vo.update.common.BaseDictUpdateVO;
import org.springblade.modules.iot.common.constant.DefValConstants;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 字典
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-04
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseDict")
@Tag(name = "字典")
public class BaseDictController extends SuperController<BaseDictService, Long, BaseDict, BaseDictSaveVO, BaseDictUpdateVO, BaseDictPageQuery, BaseDictResultVO> {

    private final EchoService echoService;
    private final BaseDictBiz baseDictBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<BaseDict> handlerWrapper(BaseDict model, PageParams<BaseDictPageQuery> params) {
        QueryWrap<BaseDict> wrap = super.handlerWrapper(null, params);
        LbQueryWrap<BaseDict> wrapper = wrap.lambda();
        wrapper.eq(BaseDict::getParentId, DefValConstants.PARENT_ID)
                .like(BaseDict::getKey, model.getKey())
                .like(BaseDict::getName, model.getName())
                .in(BaseDict::getClassify, params.getModel().getClassify())
                .in(BaseDict::getState, params.getModel().getState());

        return wrap;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return R.success(superService.deleteDict(ids));
    }

    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "key", description = "字典标识", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测字典标识是否可用", description = "检测字典标识是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String key, @RequestParam(required = false) Long id) {
        return success(superService.checkByKey(key, id));
    }


    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "导入", description = "将系统字典导入到个性字典")
    @PostMapping("/importDict")
    public R<Boolean> importDict(@RequestParam Long id) {
        return success(baseDictBiz.importDict(id));
    }

}
