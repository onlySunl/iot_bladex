package org.springblade.modules.iot.broker;

import com.alibaba.fastjson2.JSON;
import org.springblade.basic.base.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.broker.mqtt.service.MqttBrokerService;
import org.springblade.modules.iot.vo.query.KillClientRequestVO;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;
import org.springblade.modules.iot.vo.result.MqttSessionDetailsResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangyh
 * @since 2024/12/24 15:54
 */
@Service
@Slf4j
public class MqttBrokerOpenInnerFacadeImpl implements MqttBrokerOpenInnerFacade {
    @Autowired
    private MqttBrokerService mqttBrokerService;

    @Override
    public R<?> sendMessage(PublishMessageRequestVO publishMessageRequestVO) {
        log.info("Received request to send message.param {}", JSON.toJSONString(publishMessageRequestVO));
        try {
            return R.success(mqttBrokerService.publishMessage(publishMessageRequestVO));
        } catch (ServiceException e) {
            log.error("Failed to send message. param: {}", JSON.toJSONString(publishMessageRequestVO), e);
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<?> closeConnection(KillClientRequestVO killClientRequestVO) {
        log.info("Received request to close connection. param: {}", JSON.toJSONString(killClientRequestVO));
        try {
            mqttBrokerService.killClientConnection(killClientRequestVO.getTenantId(), killClientRequestVO.getUserId(), killClientRequestVO.getClientId(), killClientRequestVO.getClientType());
            return R.success();
        } catch (ServiceException e) {
            log.error("Failed to close connection. param: {}", JSON.toJSONString(killClientRequestVO), e);
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<MqttSessionDetailsResultVO> getSessionInfo(String tenantId, String userId, String clientId) {
        log.info("Received request to get session info for tenantId: {}, userId: {}, clientId: {}", tenantId, userId, clientId);

        try {
            MqttSessionDetailsResultVO mqttSessionDetailsResultVO = mqttBrokerService.getSessionInfo(tenantId, userId, clientId);
            log.info("Successfully retrieved session info for tenantId: {}, userId: {}, clientId: {}", tenantId, userId, clientId);
            return R.success(mqttSessionDetailsResultVO);
        } catch (ServiceException e) {
            log.warn("Business exception while retrieving session info for tenantId: {}, userId: {}, clientId: {}. Error: {}", tenantId, userId, clientId, e.getMessage());
            return R.fail(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while retrieving session info for tenantId: {}, userId: {}, clientId: {}. Error: {}", tenantId, userId, clientId, e.getMessage());
            return R.fail("Error retrieving session info"+e.getMessage());
        }
    }

    @Override
    public R<Boolean> isOnline(String tenantId, String deviceIdentification, String clientId) {
        return mqttBrokerService.isOnline(tenantId, deviceIdentification, clientId);
    }
}
