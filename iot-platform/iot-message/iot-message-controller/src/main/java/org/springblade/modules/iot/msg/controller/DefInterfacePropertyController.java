package org.springblade.modules.iot.msg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.base.R;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;
import org.springblade.modules.iot.msg.service.DefInterfacePropertyService;
import org.springblade.modules.iot.msg.vo.query.DefInterfacePropertyPageQuery;
import org.springblade.modules.iot.msg.vo.result.DefInterfacePropertyResultVO;
import org.springblade.modules.iot.msg.vo.save.DefInterfacePropertyBatchSaveVO;
import org.springblade.modules.iot.msg.vo.save.DefInterfacePropertySaveVO;
import org.springblade.modules.iot.msg.vo.update.DefInterfacePropertyUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 接口属性
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/defInterfaceProperty")
@Tag(name = "接口属性")
public class DefInterfacePropertyController extends SuperController<DefInterfacePropertyService, Long, DefInterfaceProperty, DefInterfacePropertySaveVO,
        DefInterfacePropertyUpdateVO, DefInterfacePropertyPageQuery, DefInterfacePropertyResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    /**
     * 新增
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存")
    @PostMapping("/batchSave")
    @WebLog(value = "保存", request = false)
    public R<Boolean> batchSave(@RequestBody @Validated DefInterfacePropertyBatchSaveVO saveVO) {
        return R.success(superService.batchSave(saveVO));

    }

}


