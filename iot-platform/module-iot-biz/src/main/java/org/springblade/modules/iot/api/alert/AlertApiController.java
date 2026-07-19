package org.springblade.modules.iot.api.alert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 告警对外API控制器，与RemoteIotAlertService接口一一对应
 */
@RestController
@RequestMapping("/alertApi")
@Tag(name = "告警API", description = "告警配置分页、生成通知消息接口")
public class AlertApiController extends BladeController {

    @Resource
    private AlertApi alertApi;

    @PostMapping("/getAlertConfigPage")
    @Operation(summary = "分页查询告警配置列表")
    public PageResult<AlertConfig> getAlertConfigPage(@RequestBody AlertConfigPageReqVO reqVO) {
        return alertApi.getAlertConfigPage(reqVO);
    }

    @PostMapping("/getNotifyMessage")
    @Operation(summary = "根据告警配置组装通知消息")
    public Message getNotifyMessage(@RequestBody AlertConfig alertConfig) {
        return alertApi.getNotifyMessage(alertConfig);
    }
}