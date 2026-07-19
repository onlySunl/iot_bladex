package org.springblade.modules.iot.api.alert;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认语音通知策略实现（空实现）
 * 当没有自定义 VMS 服务商实现时，使用此默认实现，仅打印日志
 * 用户可通过实现 ChannelVmsStrategy 接口并提供自己的 Bean 来覆盖此默认实现
 */
@Slf4j
@Component
@ConditionalOnMissingBean(ChannelVmsStrategy.class)
public class DefaultChannelVmsStrategy implements ChannelVmsStrategy {

    @Override
    public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
        log.warn("[VMS] 未配置语音通知服务商实现，跳过呼叫。templateId={}, params={}", templateId, templateParam);
    }
}
