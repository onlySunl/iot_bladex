package org.springblade.modules.iot.device;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.message.DeviceMessage;
import org.springblade.modules.iot.common.message.ServiceCallMessage;
import org.springblade.modules.iot.common.protocol.ProtocolCodec;
import org.springblade.modules.iot.common.protocol.ProtocolRegistry;
import org.springblade.modules.iot.common.protocol.ProtocolType;
import org.springblade.modules.iot.protocol.mqtt.MqttClientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 设备下行服务 - 向设备下发指令
 *
 * @author blade-iot
 */
@Slf4j
@Service
public class DeviceDownlinkService {

    /** 设备对应的 MQTT 客户端: deviceId -> MqttClientService */
    private final Map<String, MqttClientService> deviceClients = new ConcurrentHashMap<>();

    /**
     * 注册设备的 MQTT 客户端
     */
    public void registerClient(String deviceId, MqttClientService client) {
        deviceClients.put(deviceId, client);
    }

    /**
     * 移除设备的 MQTT 客户端
     */
    public void removeClient(String deviceId) {
        deviceClients.remove(deviceId);
    }

    /**
     * 下发服务调用指令
     */
    public boolean callService(String deviceId, String productId, String serviceKey,
                                Map<String, Object> params, ProtocolType protocolType) {
        MqttClientService client = deviceClients.get(deviceId);
        if (client == null || !client.isConnected()) {
            log.warn("[下行] 设备 {} 未连接", deviceId);
            return false;
        }

        try {
            ServiceCallMessage msg = new ServiceCallMessage();
            msg.setMessageId(UUID.randomUUID().toString());
            msg.setDeviceId(deviceId);
            msg.setProductId(productId);
            msg.setServiceKey(serviceKey);
            msg.setParams(params);
            msg.setTimestamp(System.currentTimeMillis());

            String topic = String.format("/sys/%s/%s/thing/service/%s", productId, deviceId, serviceKey);
            client.publishDeviceMessage(topic, msg);

            log.info("[下行] 服务调用已发送: deviceId={}, service={}", deviceId, serviceKey);
            return true;
        } catch (Exception e) {
            log.error("[下行] 服务调用失败: deviceId={}, service={}", deviceId, serviceKey, e);
            return false;
        }
    }

    /**
     * 下发属性设置指令
     */
    public boolean setProperties(String deviceId, String productId,
                                  Map<String, Object> properties, ProtocolType protocolType) {
        return callService(deviceId, productId, "property.set", properties, protocolType);
    }

    /**
     * 检查设备是否在线
     */
    public boolean isDeviceOnline(String deviceId) {
        MqttClientService client = deviceClients.get(deviceId);
        return client != null && client.isConnected();
    }
}
