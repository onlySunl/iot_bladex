package org.springblade.modules.iot.mqs.uplink.service;

import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import org.springblade.basic.base.R;
import org.springblade.modules.iot.link.facade.OtaOpenInnerFacade;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * -----------------------------------------------------------------------------
 * File Name: EventOtaCommandResponseService
 * -----------------------------------------------------------------------------
 * Description:
 * OTA 主动拉取远程升级资源包  mqtt事件业务层
 * -----------------------------------------------------------------------------
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
public class EventOtaPullService {

    @Autowired
    private OtaOpenInnerFacade otaOpenInnerFacade;


    /**
     * Handles the OTA_PULL topic event.
     *
     * @param topoOtaPullParam The OTA_PULL topic event data.
     * @return {@link Optional} of {@link TopoOtaPullResponseParam} containing the saved OTA command response.
     */
    public Optional<TopoOtaPullResponseParam> handleMqttEventOtaPull(TopoOtaPullParam topoOtaPullParam) {
        try {

            R<TopoOtaPullResponseParam> otaPullResponseParamR = otaOpenInnerFacade.otaPullByMqtt(topoOtaPullParam);
            log.info("OTA Pull response: {}", JSON.toJSONString(otaPullResponseParamR));

            if (!otaPullResponseParamR.getIsSuccess()) {
                log.error("Failed to OTA Pull response: {}", otaPullResponseParamR.getErrorMsg());
                return Optional.empty();
            }

            return Optional.of(otaPullResponseParamR.getData());
        } catch (Exception e) {
            log.error("Failed to process OTA pull event: {}", topoOtaPullParam, e);
            return Optional.empty();
        }
    }

}
