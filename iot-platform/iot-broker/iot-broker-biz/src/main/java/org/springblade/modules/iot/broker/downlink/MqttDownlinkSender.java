package org.springblade.modules.iot.broker.downlink;

import java.util.Optional;

import org.springblade.core.tool.api.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.broker.mqtt.service.MqttBrokerService;
import org.springblade.modules.iot.vo.query.DownlinkCommand;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MQTT 下行 ── 直调 broker 的 {@link MqttBrokerService}(经 BifroMQ,按 topic 路由到订阅设备)。
 *
 * @author mqttsnet
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MqttDownlinkSender implements DownlinkChannelSender {

    private final MqttBrokerService mqttBrokerService;

    @Override
    public String supportedProtocol() {
        return DownlinkProtocols.MQTT;
    }

    @Override
    public R<?> send(DownlinkCommand command) {
        PublishMessageRequestVO vo = new PublishMessageRequestVO()
            .setReqId(command.getReqId())
            .setTenantId(command.getTenantId())
            .setTopic(command.getTopic())
            .setQos(command.getQos())
            .setClientType(Optional.ofNullable(command.getClientType()).orElse("web"))
            .setExpirySeconds(Optional.ofNullable(command.getExpirySeconds()).orElse("3600"));
        // 负载:显式指定 forceBase64Decode 则照办;否则自动探测(等价历史 setPayloadData(message))
        if (command.getForceBase64Decode() != null) {
            vo.setPayload(command.getPayload()).setForceBase64Decode(command.getForceBase64Decode());
        } else {
            vo.setPayloadData(command.getPayload());
        }
        try {
            return R.success(mqttBrokerService.publishMessage(vo));
        } catch (ServiceException e) {
            log.error("[downlink.mqtt] send failed topic={} err={}", command.getTopic(), e.getMessage());
            return R.fail(e.getMessage());
        }
    }
}
