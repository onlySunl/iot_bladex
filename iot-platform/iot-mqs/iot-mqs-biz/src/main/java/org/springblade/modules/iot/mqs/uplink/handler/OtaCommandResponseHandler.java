package org.springblade.modules.iot.mqs.uplink.handler;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.common.iot.constant.CommonIotConstants;
import org.springblade.core.protocol.factory.ProtocolMessageAdapter;
import org.springblade.core.protocol.model.EncryptionDetailsDTO;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.entity.uplink.source.UplinkMessageEventSource;
import org.springblade.modules.iot.link.facade.DeviceOpenInnerFacade;
import org.springblade.modules.iot.mqs.uplink.handler.factory.AbstractMessageHandler;
import org.springblade.modules.iot.mqs.uplink.service.EventOtaCommandResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: iot-platform
 * @description: 处理OTA_COMMAND_RESPONSE主题mqtt.handler
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2024-01-15 23:00
 **/
@Slf4j
@Service
public class OtaCommandResponseHandler extends AbstractMessageHandler implements TopicHandler {
    @Autowired
    private EventOtaCommandResponseService mqttEventOtaCommandResponseService;

    public OtaCommandResponseHandler(LinkCacheDataHelper linkCacheDataHelper,
                                     DeviceOpenInnerFacade deviceOpenInnerApi,
                                     ProtocolMessageAdapter protocolMessageAdapter) {
        super(linkCacheDataHelper, deviceOpenInnerApi, protocolMessageAdapter);
    }

    /**
     * 本处理器完整匹配的 topic 正则。
     *
     * @return OTA_COMMAND_RESPONSE 主题正则
     * @author mqttsnet
     * @since 2026-06-03
     */
    @Override
    public String topicPattern() {
        return "^/([^/]+)/devices/([^/]+)/topo/otaCommandResponse$";
    }

    /**
     * Handles MQTT messages, decrypts them, and processes the command.
     *
     * @param eventSource The MQTT message event source.
     */
    @Override
    public void handle(UplinkMessageEventSource eventSource) {
        String topic = eventSource.getTopic();
        String qos = eventSource.getQos();
        byte[] payloadBytes = eventSource.getPayloadBytes();
        String body = StrUtil.str(payloadBytes, StandardCharsets.UTF_8);
        log.info("Received OTA_COMMAND_RESPONSE message: topic: {}, qos: {}, payload(body): {}", topic, qos, body);
        if (!protocolMessageAdapter.validateProtocolData(body)) {
            log.warn("The protocol format is incorrect");
            return;
        }

        Map<String, String> variables = protocolMessageAdapter.extractVariables(topic);
        String deviceId = variables.get(CommonIotConstants.DEVICE_ID);

        DeviceCacheVO deviceCacheVO = resolveDeviceCache(eventSource, deviceId);
        if (deviceCacheVO == null) {
            log.warn("Device with ID {} not found.", deviceId);
            return;
        }

        try {
            EncryptionDetailsDTO encryptionDetailsDTO = EncryptionDetailsDTO.builder()
                .signKey(deviceCacheVO.getSignKey())
                .encryptKey(deviceCacheVO.getEncryptKey())
                .encryptVector(deviceCacheVO.getEncryptVector())
                .cipherFlag(deviceCacheVO.getEncryptMethod())
                .build();
            String decryptedBody = protocolMessageAdapter.decryptMessage(body, encryptionDetailsDTO);
            mqttEventOtaCommandResponseService.saveMqttEventOtaCommandResponse(deviceCacheVO, decryptedBody);
        } catch (Exception e) {
            log.error("Failed to decrypt the message", e);
        }
    }


    /**
     * Processes the message and returns the response body.
     *
     * @param messageParam The message body.
     * @return The response body.
     * @throws Exception If an error occurs while processing the message.
     */
    @Override
    protected String processingTopicMessage(Object messageParam) throws Exception {

        return null;
    }

}
