package org.springblade.modules.iot.device;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.message.DeviceMessage;
import org.springblade.modules.iot.common.message.PropertyMessage;
import org.springblade.modules.iot.common.message.EventMessage;
import org.springblade.modules.iot.common.protocol.ProtocolRegistry;
import org.springblade.modules.iot.common.protocol.ProtocolCodec;
import org.springblade.modules.iot.common.protocol.ProtocolType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * 设备消息处理服务 - 处理设备上行消息并分发到对应的处理器
 *
 * @author blade-iot
 */
@Slf4j
@Service
public class DeviceMessageService {

    /** 设备消息处理器: deviceId -> handler */
    private final Map<String, Consumer<DeviceMessage>> deviceHandlers = new ConcurrentHashMap<>();

    /** 全局消息监听器 */
    private final List<Consumer<DeviceMessage>> globalListeners = new java.util.concurrent.CopyOnWriteArrayList<>();

    /**
     * 处理设备上行消息
     */
    public void handleUplinkMessage(DeviceMessage message) {
        if (message == null) {
            return;
        }
        log.debug("[设备消息] 收到上行消息: deviceId={}, type={}", message.getDeviceId(), message.getMessageType());

        // 分发给设备专用处理器
        if (message.getDeviceId() != null) {
            Consumer<DeviceMessage> handler = deviceHandlers.get(message.getDeviceId());
            if (handler != null) {
                handler.accept(message);
            }
        }

        // 分发给全局监听器
        for (Consumer<DeviceMessage> listener : globalListeners) {
            try {
                listener.accept(message);
            } catch (Exception e) {
                log.error("[设备消息] 全局监听器处理异常", e);
            }
        }
    }

    /**
     * 使用协议解码器处理原始数据
     */
    public void handleRawData(String deviceId, String productId, ProtocolType protocolType,
                               byte[] payload, String topic) {
        ProtocolCodec codec = ProtocolRegistry.getCodec(protocolType);
        if (codec == null) {
            log.warn("[设备消息] 未找到协议编解码器: {}", protocolType);
            return;
        }

        List<DeviceMessage> messages = codec.decode(payload, topic);
        for (DeviceMessage msg : messages) {
            if (msg.getDeviceId() == null) {
                msg.setDeviceId(deviceId);
            }
            if (msg.getProductId() == null) {
                msg.setProductId(productId);
            }
            handleUplinkMessage(msg);
        }
    }

    /**
     * 注册设备消息处理器
     */
    public void registerHandler(String deviceId, Consumer<DeviceMessage> handler) {
        deviceHandlers.put(deviceId, handler);
    }

    /**
     * 移除设备消息处理器
     */
    public void removeHandler(String deviceId) {
        deviceHandlers.remove(deviceId);
    }

    /**
     * 添加全局消息监听器
     */
    public void addGlobalListener(Consumer<DeviceMessage> listener) {
        globalListeners.add(listener);
    }

    /**
     * 移除全局消息监听器
     */
    public void removeGlobalListener(Consumer<DeviceMessage> listener) {
        globalListeners.remove(listener);
    }
}
