package org.springblade.modules.iot.api.alert;


import org.springblade.modules.iot.api.alert.dto.VmsConfig;

import java.util.Map;

public interface ChannelVmsStrategy {
    void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig smsConfig);
}
