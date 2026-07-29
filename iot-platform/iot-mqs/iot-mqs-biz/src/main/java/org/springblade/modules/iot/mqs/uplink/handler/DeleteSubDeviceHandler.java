package org.springblade.modules.iot.mqs.uplink.handler;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.basic.base.R;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.protocol.factory.ProtocolMessageAdapter;
import org.springblade.basic.protocol.model.EncryptionDetailsDTO;
import org.springblade.basic.protocol.model.ProtocolDataMessageDTO;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.CommonIotConstants;
import org.springblade.modules.iot.entity.uplink.source.UplinkMessageEventSource;
import org.springblade.modules.iot.link.facade.DeviceOpenInnerFacade;
import org.springblade.modules.iot.mqs.uplink.handler.factory.AbstractMessageHandler;
import org.springblade.modules.iot.protocol.vo.param.TopoDeleteSubDeviceParam;
import org.springblade.modules.iot.protocol.vo.result.TopoDeviceOperationResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: iot-platform
 * @description: 处理DELETE_SUB_DEVICE主题
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-05 22:59
 **/
@Slf4j
@Service
public class DeleteSubDeviceHandler extends AbstractMessageHandler implements TopicHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public DeleteSubDeviceHandler(LinkCacheDataHelper linkCacheDataHelper,
                                  DeviceOpenInnerFacade deviceOpenInnerApi,
                                  ProtocolMessageAdapter protocolMessageAdapter) {
        super(linkCacheDataHelper, deviceOpenInnerApi, protocolMessageAdapter);
    }

    /**
     * 本处理器完整匹配的 topic 正则。
     *
     * @return DELETE_SUB_DEVICE 主题正则
     * @author mqttsnet
     * @since 2026-06-03
     */
    @Override
    public String topicPattern() {
        return "^/([^/]+)/devices/([^/]+)/topo/delete$";
    }

    /**
     * 处理DELETE_SUB_DEVICE主题的MQTT消息
     *
     * @param eventSource the MQTT message event source.
     */
    @Override
    public void handle(UplinkMessageEventSource eventSource) {
        String topic = eventSource.getTopic();
        String qos = eventSource.getQos();
        byte[] payloadBytes = eventSource.getPayloadBytes();
        String body = StrUtil.str(payloadBytes, StandardCharsets.UTF_8);
        log.info("Received DELETE_SUB_DEVICE message: topic={}, qos={}, body={}", topic, qos, body);
        if (!protocolMessageAdapter.validateProtocolData(body)) {
            log.warn("The protocol format is incorrect");
            return;
        }

        // Extract variables from the topic
        Map<String, String> stringStringMap = protocolMessageAdapter.extractVariables(topic);
        String version = stringStringMap.get(CommonIotConstants.VERSION);
        String deviceId = stringStringMap.get(CommonIotConstants.DEVICE_ID);

        DeviceCacheVO deviceCacheVO = resolveDeviceCache(eventSource, deviceId);
        if (deviceCacheVO == null) {
            log.warn("Device {} not found in cache", deviceId);
            return;
        }

        try {
            ProtocolDataMessageDTO protocolDataMessageDTO = protocolMessageAdapter.parseProtocolDataMessage(body);
            // 构造 EncryptionDetails 对象
            EncryptionDetailsDTO encryptionDetailsDTO = EncryptionDetailsDTO.builder()
                .signKey(deviceCacheVO.getSignKey())
                .encryptKey(deviceCacheVO.getEncryptKey())
                .encryptVector(deviceCacheVO.getEncryptVector())
                .cipherFlag(deviceCacheVO.getEncryptMethod())
                .build();
            String dataBody = protocolMessageAdapter.decryptMessage(body, encryptionDetailsDTO);

            // Parse body
            TopoDeleteSubDeviceParam topoDeleteSubDeviceParam = JSON.parseObject(dataBody, TopoDeleteSubDeviceParam.class);
            topoDeleteSubDeviceParam.setGatewayIdentification(deviceId);
            String resultDataBody = processingTopicMessage(topoDeleteSubDeviceParam);

            // Handle result
            ProtocolDataMessageDTO handleResult = protocolMessageAdapter.buildResponse(protocolDataMessageDTO, resultDataBody, encryptionDetailsDTO);

            // Determine response topic based on request topic
            String responseTopic = "/topo/deleteResponse";
            // Generate response topic string
            String responseTopicStr = generateResponseTopic(version, deviceId, responseTopic);

            // 序列化 handleResult 对象为 JSON 字符串
            String resultData = OBJECT_MAPPER.writeValueAsString(handleResult);

            // Push message to MQTT to notify device of successful/failed sub-device deletion
            sendMessage(responseTopicStr, qos, resultData, ContextUtil.getTenantIdStr(), deviceCacheVO);
        } catch (Exception e) {
            log.error("Failed to decrypt the message", e);
        }
    }

    /**
     * Process /topo/delete Topic for gateway device to delete sub-device
     *
     * @param topoDeleteSubDeviceParam delete device data
     * @return Processing result json
     */
    @Override
    protected String processingTopicMessage(Object topoDeleteSubDeviceParam) throws Exception {
        R<TopoDeviceOperationResultVO> mqttTopoDeleteDeviceResultVOR = deviceOpenInnerApi.deleteSubDeviceByMqtt((TopoDeleteSubDeviceParam) topoDeleteSubDeviceParam);
        log.info("processingTopoDeleteTopic Processing result:{}", JSON.toJSONString(mqttTopoDeleteDeviceResultVOR));
        return JSON.toJSONString(mqttTopoDeleteDeviceResultVOR.getData());
    }
}

