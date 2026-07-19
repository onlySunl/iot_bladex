package org.springblade.modules.iot.api.alert.service;

import org.springblade.modules.iot.api.alert.dto.CallByTtsRequest;
import org.springblade.modules.iot.api.alert.factory.RemoteIotChannelVmsFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 语音通知远程Feign调用接口，对齐 {@link ChannelVmsStrategy}
 */
@FeignClient(contextId = "remoteIotChannelVmsService",
        value = IotServiceNameConstants.IOT_ALERT_VMS,
        fallbackFactory = RemoteIotChannelVmsFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotChannelVmsService {

    /** TTS语音呼叫 */
    @PostMapping("/channelVms/callByTts")
    void callByTts(@RequestBody CallByTtsRequest request);
}