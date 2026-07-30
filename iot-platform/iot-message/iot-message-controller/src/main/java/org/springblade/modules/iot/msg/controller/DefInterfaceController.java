package org.springblade.modules.iot.msg.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.base.R;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.modules.iot.msg.entity.DefInterface;
import org.springblade.modules.iot.msg.service.DefInterfaceService;
import org.springblade.modules.iot.msg.vo.query.DefInterfacePageQuery;
import org.springblade.modules.iot.msg.vo.result.DefInterfaceResultVO;
import org.springblade.modules.iot.msg.vo.save.DefInterfaceSaveVO;
import org.springblade.modules.iot.msg.vo.update.DefInterfaceUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springblade.common.iot.constant.SwaggerConstants.DATA_TYPE_LONG;
import static org.springblade.common.iot.constant.SwaggerConstants.DATA_TYPE_STRING;

/**
 * <p>
 * 前端控制器
 * 接口
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/defInterface")
@Tag(name = "接口")
public class DefInterfaceController extends SuperController<DefInterfaceService, Long, DefInterface, DefInterfaceSaveVO,
        DefInterfaceUpdateVO, DefInterfacePageQuery, DefInterfaceResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "code", description = "编码", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测资源编码是否可用", description = "检测资源编码是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String code) {
        return success(superService.check(code, id));
    }
}


