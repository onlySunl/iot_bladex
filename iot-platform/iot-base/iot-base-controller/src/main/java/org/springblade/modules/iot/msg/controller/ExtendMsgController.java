package org.springblade.modules.iot.msg.controller;

import org.springblade.basic.base.R;
import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.msg.biz.MsgBiz;
import org.springblade.modules.iot.msg.entity.ExtendMsg;
import org.springblade.modules.iot.msg.enumeration.SourceType;
import org.springblade.modules.iot.msg.service.ExtendMsgService;
import org.springblade.modules.iot.msg.vo.query.ExtendMsgPageQuery;
import org.springblade.modules.iot.msg.vo.result.ExtendMsgResultVO;
import org.springblade.modules.iot.msg.vo.save.ExtendMsgSaveVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgPublishVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @create [2022-07-10 11:41:17] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendMsg")
@Tag(name = "消息")
public class ExtendMsgController extends SuperController<ExtendMsgService, Long, ExtendMsg, ExtendMsgSaveVO,
        ExtendMsgUpdateVO, ExtendMsgPageQuery, ExtendMsgResultVO> {
    private final EchoService echoService;
    private final MsgBiz msgBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Operation(summary = "根据模板发送消息", description = "根据模板发送消息")
    @PostMapping("/sendByTemplate")
    @WebLog("发送消息")
    public R<Boolean> sendByTemplate(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgSendVO data) {
        return R.success(msgBiz.sendByTemplate(data, AuthUtil.getUser()));
    }

    @Operation(summary = "发布站内信", description = "发布站内信")
    @PostMapping("/publish")
    @WebLog("发布站内信")
    public R<Boolean> publish(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgPublishVO data) {

        return R.success(msgBiz.publish(data, AuthUtil.getUser()));
    }

    @Override
    public QueryWrap<ExtendMsg> handlerWrapper(ExtendMsg model, PageParams<ExtendMsgPageQuery> params) {
        QueryWrap<ExtendMsg> queryWrap = super.handlerWrapper(model, params);
        queryWrap.lambda().eq(ExtendMsg::getChannel, SourceType.APP);
        //DataScopeHelper.startDataScope("extend_msg");
        return queryWrap;
    }

    /**
     * 查询消息中心
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Operation(summary = "查询消息中心", description = "查询消息中心")
    @GetMapping("/{id:[0-9]+}")
    @WebLog("查询消息中心")
    @Override
    public R<ExtendMsgResultVO> get(@PathVariable Long id) {
        return R.success(superService.getResultById(id));
    }


}


