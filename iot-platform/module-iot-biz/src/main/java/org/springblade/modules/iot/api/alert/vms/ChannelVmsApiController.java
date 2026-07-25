package org.springblade.modules.iot.api.alert.vms;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.modules.iot.api.alert.dto.CallByTtsRequest;
import org.springblade.modules.iot.api.alert.vms.ChannelVmsStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springblade.core.boot.ctrl.BladeController;

import jakarta.annotation.Resource;

/**
 * 语音通知渠道对外API控制器
 */
@RestController
@RequestMapping("/channelVmsApi")
@Tag(name = "语音通知渠道API", description = "TTS语音呼叫接口")
public class ChannelVmsApiController extends BladeController {

    @Resource
    private ChannelVmsStrategy channelVmsStrategy;

    @PostMapping("/callByTts")
    @Operation(summary = "发起TTS语音呼叫")
    public void callByTts(@RequestBody CallByTtsRequest request) {
        channelVmsStrategy.callByTts(request.getTemplateParam(), request.getTemplateId(), request.getVmsConfig());
    }
}