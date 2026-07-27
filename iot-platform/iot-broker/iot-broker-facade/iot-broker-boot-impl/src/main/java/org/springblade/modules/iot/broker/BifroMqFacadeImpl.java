package org.springblade.modules.iot.broker;

import org.springblade.modules.iot.broker.api.BifroMQApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * BifroMq Facade  （job在使用）
 *
 * @author tangyh
 * @since 2024/12/24 15:53
 */
@Service
public class BifroMqFacadeImpl implements BifroMqFacade {

    @Autowired
    @Lazy
    private BifroMQApi bifroMQApi;

    @Override
    public ResponseEntity<String> publishMessage(Long reqId, String tenantId, String topic, String qos, String expirySeconds, String clientType, byte[] payload) {
        return bifroMQApi.publishMessage(reqId, tenantId, topic, qos, expirySeconds, clientType, payload);
    }

    @Override
    public ResponseEntity<String> retainMessage(Long reqId, String tenantId, String topic, String qos, String expirySeconds, String clientType, Map<String, String> clientMetadata, String payload) {
        return bifroMQApi.retainMessage(reqId, tenantId, topic, qos, expirySeconds, clientType, clientMetadata, payload);
    }

    @Override
    public ResponseEntity<String> getSessionInfo(Long reqId, String tenantId, String userId, String clientId) {
        return bifroMQApi.getSessionInfo(reqId, tenantId, userId, clientId);
    }

    @Override
    public ResponseEntity<String> expireSession(Long reqId, String tenantId, String expirySeconds) {
        return bifroMQApi.expireSession(reqId, tenantId, expirySeconds);
    }

    @Override
    public ResponseEntity<String> killClientConnection(Long reqId, String tenantId, String userId, String clientId, String clientType, Map<String, String> clientMetadata) {
        return bifroMQApi.killClientConnection(reqId, tenantId, userId, clientId, clientType, clientMetadata);
    }

    @Override
    public ResponseEntity<String> addTopicSubscription(Long reqId, String tenantId, String userId, String clientId, String topicFilter, String subQos) {
        return bifroMQApi.addTopicSubscription(reqId, tenantId, userId, clientId, topicFilter, subQos);
    }

    @Override
    public ResponseEntity<String> removeTopicSubscription(Long reqId, String tenantId, String userId, String clientId, String topicFilter) {
        return bifroMQApi.removeTopicSubscription(reqId, tenantId, userId, clientId, topicFilter);
    }
}
