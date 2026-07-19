package org.springblade.modules.iot.api.alert.vms;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 阿里云语音通知策略实现
 * 需要引入阿里云语音 SDK: com.aliyun:dyvmsapi20170525
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "config.alert.vms-provider", havingValue = "aliyun", matchIfMissing = true)
public class ChannelVmsAliyunStrategy implements ChannelVmsStrategy {
    private static final String ALI_END_POINT = "dyvmsapi.aliyuncs.com";

    @Override
    public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
        // TODO: 需要引入阿里云 SDK 后实现
        // com.aliyun:dyvmsapi20170525
        log.warn("[VMS-Aliyun] 阿里云语音通知未实现，需要引入 dyvmsapi SDK。templateId={}", templateId);
    }
}
