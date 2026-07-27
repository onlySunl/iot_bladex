package org.springblade.modules.iot.mqs.uplink.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.enumeration.DeviceCommandStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceCommandTypeEnum;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.link.facade.DeviceOpenInnerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: thinglinks-cloud
 * @description: EventCommandService
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-11-12 16:09
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class EventCommandService {

    @Autowired
    private DeviceOpenInnerFacade deviceOpenInnerApi;

    /**
     * Processes the received message, converts it to a DeviceCommandSaveVO, and saves the command.
     *
     * @param deviceCacheVO The cached device information.
     * @param topic         The MQTT topic that carried this response.
     * @param body          The body of the MQTT message.
     * @param dataBody      The body of the MQTT dataBody  message.
     * @return A JSON string representing the saved device command.
     */
    public String processCommand(DeviceCacheVO deviceCacheVO, String topic, String body, String dataBody) {
        DeviceCommandSaveVO saveVO = convertToSaveVO(deviceCacheVO, topic, body, dataBody);
        R<DeviceCommand> response = deviceOpenInnerApi.saveDeviceCommand(saveVO);

        if (Boolean.FALSE.equals(R.isSuccess(response))) {
            log.error("Failed to process device command: {}", JSON.toJSONString(response));
            throw new IllegalStateException("Failed to save device command");
        }

        log.info("Device command processed and saved: {}", JSON.toJSONString(response.getData()));
        return JSON.toJSONString(response.getData());
    }

    /**
     * Converts the received message body to a DeviceCommandSaveVO object.
     *
     * @param deviceCacheVO The cached device information.
     * @param topic         The MQTT topic that carried this response.
     * @param body          The body of the MQTT message.
     * @param dataBody      The body of the MQTT dataBody message.
     * @return The DeviceCommandSaveVO object.
     */
    private DeviceCommandSaveVO convertToSaveVO(DeviceCacheVO deviceCacheVO, String topic, String body, String dataBody) {
        DeviceCommandSaveVO saveVO = new DeviceCommandSaveVO();
        saveVO.setDeviceIdentification(deviceCacheVO.getDeviceIdentification());
        saveVO.setCommandType(DeviceCommandTypeEnum.COMMAND_RESPONSE.getValue());
        // 鏀跺埌鍝嶅簲鍗宠惤搴?鍛戒护鎴愯触(errCode)銆乻erviceCode/cmd 鐢卞墠绔粠 remark(deviceRsp)瑙ｆ瀽灞曠ず,鍚庣涓嶅啀瑙ｆ瀽
        saveVO.setStatus(DeviceCommandStatusEnum.SUCCESS.getValue());
        JSONObject raw = new JSONObject();
        raw.put("topic", topic);
        raw.put("payload", body);
        saveVO.setContent(raw.toJSONString());
        saveVO.setRemark(dataBody);
        return saveVO;
    }
}
