package org.springblade.modules.iot.broker.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.broker.MqttBrokerOpenInnerFacade;
import org.springblade.modules.iot.broker.api.MqttBrokerOpenInnerApi;
import org.springblade.modules.iot.vo.query.KillClientRequestVO;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;
import org.springblade.modules.iot.vo.result.MqttSessionDetailsResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangyh
 * @since 2024/12/24 15:54
 */
@Service
public class MqttBrokerOpenInnerFacadeImpl implements MqttBrokerOpenInnerFacade {
    @Autowired
    @Lazy
    private MqttBrokerOpenInnerApi mqttBrokerOpenInnerApi;

    @Override
    public R<?> sendMessage(PublishMessageRequestVO publishMessageRequestVO) {
        return mqttBrokerOpenInnerApi.sendMessage(publishMessageRequestVO);
    }

    @Override
    public R<?> closeConnection(KillClientRequestVO killClientRequestVO) {
        return mqttBrokerOpenInnerApi.closeConnection(killClientRequestVO);
    }

    @Override
    public R<MqttSessionDetailsResultVO> getSessionInfo(String tenantId, String userId, String clientId) {
        return mqttBrokerOpenInnerApi.getSessionInfo(tenantId, userId, clientId);
    }

    @Override
    public R<Boolean> isOnline(String tenantId, String deviceIdentification, String clientId) {
        return mqttBrokerOpenInnerApi.isOnline(tenantId, deviceIdentification, clientId);
    }
}
