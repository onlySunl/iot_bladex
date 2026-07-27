package org.springblade.modules.iot.mqs.uplink.handler;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import org.springblade.common.protocol.ProtocolMessageAdapter;
import org.springblade.modules.iot.common.protocol.EncryptionDetailsDTO;
import org.springblade.common.utils.StrPool;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.common.constant.CommonIotConstants;
import org.springblade.modules.iot.entity.uplink.source.UplinkMessageEventSource;
import org.springblade.modules.iot.link.facade.DeviceOpenInnerFacade;
import org.springblade.modules.iot.mqs.uplink.handler.factory.AbstractMessageHandler;
import org.springblade.modules.iot.mqs.service.DeviceDataProcessingService;
import org.springblade.modules.iot.protocol.vo.param.TopoDeviceDataReportParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: thinglinks-cloud
 * @description: ????????VICE_DATA??????? * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-05 23:00
 **/
@Slf4j
@Service
public class DeviceDatasHandler extends AbstractMessageHandler implements TopicHandler {

    @Autowired
    private DeviceDataProcessingService deviceDataProcessingService;


    public DeviceDatasHandler(LinkCacheDataHelper linkCacheDataHelper,
                              DeviceOpenInnerFacade deviceOpenInnerApi,
                              ProtocolMessageAdapter protocolMessageAdapter) {
        super(linkCacheDataHelper, deviceOpenInnerApi, protocolMessageAdapter);
    }

    /**
     * ?????????????????????????????topic ??????????     *
     * @return DEVICE_DATA ??????????????     * @author mqttsnet
     * @since 2026-06-03
     */
    @Override
    public String topicPattern() {
        return "^/([^/]+)/devices/([^/]+)/datas$";
    }

    /**
     * ???????????????????????????????????????ICE_DATA??????????     *
     * @param eventSource ????????TT??????????b????????????????     */
    @Override
    public void handle(UplinkMessageEventSource eventSource) {
        String topic = eventSource.getTopic();
        String qos = eventSource.getQos();
        byte[] payloadBytes = eventSource.getPayloadBytes();
        String body = StrUtil.str(payloadBytes, StandardCharsets.UTF_8);
        log.info("Received DEVICE_DATA message: topic: {}, qos: {}, payload(body): {}", topic, qos, body);
        // Extract variables from the topic
        Map<String, String> stringStringMap = protocolMessageAdapter.extractVariables(topic);
        String deviceId = stringStringMap.get(CommonIotConstants.DEVICE_ID);

        DeviceCacheVO deviceCacheVO = resolveDeviceCache(eventSource, deviceId);
        if (deviceCacheVO == null) {
            log.warn("Device with ID {} not found in cache, skipping processing.", deviceId);
            return;
        }
        // ?????????h???topic/?????????????????????????????????????????????????????????????InboundScriptTransformer)???????????????????????????????????????????????????????????????????        // ????????????????????        if (!JSON.isValid(body) || !protocolMessageAdapter.validateProtocolData(body)) {
            log.warn("?????????????????????????????????????????: {}, ??????? {}", deviceCacheVO.getDeviceIdentification(), body);
            return;
        }

        try {
            // ???????EncryptionDetails ???????            EncryptionDetailsDTO encryptionDetailsDTO = EncryptionDetailsDTO.builder()
                .signKey(deviceCacheVO.getSignKey())
                .encryptKey(deviceCacheVO.getEncryptKey())
                .encryptVector(deviceCacheVO.getEncryptVector())
                .cipherFlag(deviceCacheVO.getEncryptMethod())
                .build();
            String dataBody = protocolMessageAdapter.decryptMessage(body, encryptionDetailsDTO);

            if (StrUtil.isBlank(dataBody)) {
                log.warn("??????????????????????????????????????????????? {}", deviceCacheVO.getDeviceIdentification());
                return;
            }
            TopoDeviceDataReportParam dataReportParam = JSON.parseObject(dataBody, TopoDeviceDataReportParam.class);
            processingTopicMessage(dataReportParam);
        } catch (Exception e) {
            log.error("?????????????????????????????????????????????????? {}, ??????? {}", deviceCacheVO.getDeviceIdentification(), e.getMessage(), e);

        }
    }

    /**
     * Process /device/data Topic for device data reporting
     *
     * @param deviceDataParam device data
     * @return Processing result json
     */
    @Override
    protected String processingTopicMessage(Object deviceDataParam) throws Exception {
        deviceDataProcessingService.processDeviceDataReport((TopoDeviceDataReportParam) deviceDataParam);
        return StrPool.EMPTY;
    }


}
