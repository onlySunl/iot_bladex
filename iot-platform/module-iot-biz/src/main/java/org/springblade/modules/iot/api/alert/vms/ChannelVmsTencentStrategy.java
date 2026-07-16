package org.springblade.modules.iot.api.alert.vms;

import org.springblade.modules.iot.api.alert.ChannelVmsStrategy;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@ConditionalOnProperty(name = "config.alert.vms-provider", havingValue = "tencent")
public class ChannelVmsTencentStrategy implements ChannelVmsStrategy {
    @Override
    public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
        // todo
    }

}
