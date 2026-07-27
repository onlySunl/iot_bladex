package org.springblade.modules.iot.broker.api.hystrix;

import org.springblade.modules.iot.broker.api.BifroMQApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * -----------------------------------------------------------------------------
 * File Name: BifroMQApiFallback.java
 * -----------------------------------------------------------------------------
 * Description:
 * BifroMQApi API熔断
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-10-31 12:31
 */
@Component
public class BifroMQApiFallback implements BifroMQApi {


    @Override
    public ResponseEntity<String> publishMessage(Long reqId, String tenantId, String topic, String qos, String expirySeconds, String clientType, byte[] payload) {
        return new ResponseEntity<>("BifroMQApiFallback.publishMessage() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> retainMessage(Long reqId, String tenantId, String topic, String qos, String expirySeconds, String clientType, Map<String, String> clientMetadata, String payload) {
        return new ResponseEntity<>("BifroMQApiFallback.retainMessage() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getSessionInfo(Long reqId, String tenantId, String userId, String clientId) {
        return new ResponseEntity<>("BifroMQApiFallback.getSessionInfo() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> expireSession(Long reqId, String tenantId, String expirySeconds) {
        return new ResponseEntity<>("BifroMQApiFallback.expireSession() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> killClientConnection(Long reqId, String tenantId, String userId, String clientId, String clientType, Map<String, String> clientMetadata) {
        return new ResponseEntity<>("BifroMQApiFallback.killClientConnection() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addTopicSubscription(Long reqId, String tenantId, String userId, String clientId, String topicFilter, String subQos) {
        return new ResponseEntity<>("BifroMQApiFallback.addTopicSubscription() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> removeTopicSubscription(Long reqId, String tenantId, String userId, String clientId, String topicFilter) {
        return new ResponseEntity<>("BifroMQApiFallback.removeTopicSubscription() Service call failure e:{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
