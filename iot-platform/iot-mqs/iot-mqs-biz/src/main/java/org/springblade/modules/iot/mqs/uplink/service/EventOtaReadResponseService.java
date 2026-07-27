package org.springblade.modules.iot.mqs.uplink.service;

import com.alibaba.fastjson2.JSON;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.facade.OtaOpenInnerFacade;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * -----------------------------------------------------------------------------
 * File Name: EventOtaCommandResponseService
 * -----------------------------------------------------------------------------
 * Description:
 * OTA ???????????????????????????? mqtt???????$???????? * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/1/18       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/03/15 15:38
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventOtaReadResponseService {

    @Autowired
    private OtaOpenInnerFacade otaOpenInnerFacade;


    /**
     * Handles the OTA_READ_RESPONSE topic event.
     *
     * @param topoOtaReadResponseParam The OTA_READ_RESPONSE topic event data.
     */
    public void handleMqttEventOtaReadResponse(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        try {
            R<?> otaReadResponseParamR = otaOpenInnerFacade.otaReadResponseByMqtt(topoOtaReadResponseParam);
            log.info("OTA Read response: {}", JSON.toJSONString(otaReadResponseParamR));

            if (!R.isSuccess(otaReadResponseParamR)) {
                log.error("Failed to save OTA command response: {}", otaReadResponseParamR.getErrorMsg());
            }

        } catch (Exception e) {
            log.error("Failed to process OTA Read Response: {}", topoOtaReadResponseParam, e);
        }
    }

}
