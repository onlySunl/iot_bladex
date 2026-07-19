package org.springblade.modules.iot.api.alert.vms;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.ChannelVmsStrategy;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@ConditionalOnProperty(name = "config.alert.vms-provider", havingValue = "tencent")
public class ChannelVmsTencentStrategy implements ChannelVmsStrategy {

    @Override
    public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
        // TODO: 实现腾讯云语音通知
        log.warn("[VMS-Tencent] 腾讯云语音通知未实现。templateId={}", templateId);
    }
}
