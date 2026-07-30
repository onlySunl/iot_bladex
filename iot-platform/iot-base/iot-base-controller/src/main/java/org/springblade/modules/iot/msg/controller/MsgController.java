package org.springblade.modules.iot.msg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.base.R;
import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.msg.biz.MsgBiz;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 消息
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/inner")
@Tag(name = "inner-消息模版")
public class MsgController {
    private final MsgBiz msgBiz;


    @Operation(summary = "根据模板发送消息", description = "根据模板发送消息")
    @PostMapping("/extendMsg/sendByTemplate")
    @WebLog("发送消息")
    public R<Boolean> sendByTemplate(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgSendVO data) {
        return R.success(msgBiz.sendByTemplate(data, AuthUtil.getUser()));
    }

}


