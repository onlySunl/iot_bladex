package org.springblade.modules.iot.push;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.message.DeviceMessage;
import org.springblade.modules.iot.push.strategy.HttpPushStrategy;
import org.springblade.modules.iot.push.strategy.MqttPushStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 推送策略管理器 - 管理和调度数据推送策略
 *
 * @author blade-iot
 */
@Slf4j
@Service
public class PushStrategyManager {

    private final Map<String, PushStrategy> strategies = new ConcurrentHashMap<>();

    /**
     * 注册推送策略
     */
    public void registerStrategy(String id, String type, String config) {
        PushStrategy strategy = createStrategy(type);
        if (strategy != null) {
            strategy.init(config);
            strategies.put(id, strategy);
            log.info("[推送管理] 注册策略: id={}, type={}", id, type);
        }
    }

    /**
     * 移除推送策略
     */
    public void removeStrategy(String id) {
        PushStrategy strategy = strategies.remove(id);
        if (strategy != null) {
            strategy.close();
            log.info("[推送管理] 移除策略: id={}", id);
        }
    }

    /**
     * 推送消息到所有策略
     */
    public void pushToAll(DeviceMessage message) {
        for (Map.Entry<String, PushStrategy> entry : strategies.entrySet()) {
            try {
                if (entry.getValue().isAvailable()) {
                    entry.getValue().push(message);
                }
            } catch (Exception e) {
                log.error("[推送管理] 推送失败: id={}", entry.getKey(), e);
            }
        }
    }

    /**
     * 推送消息到指定策略
     */
    public boolean pushTo(String id, DeviceMessage message) {
        PushStrategy strategy = strategies.get(id);
        if (strategy != null && strategy.isAvailable()) {
            return strategy.push(message);
        }
        return false;
    }

    /**
     * 获取所有策略
     */
    public Map<String, PushStrategy> getAllStrategies() {
        return Map.copyOf(strategies);
    }

    private PushStrategy createStrategy(String type) {
        return switch (type.toUpperCase()) {
            case "HTTP" -> new HttpPushStrategy();
            case "MQTT" -> new MqttPushStrategy();
            default -> {
                log.warn("[推送管理] 不支持的推送类型: {}", type);
                yield null;
            }
        };
    }
}
