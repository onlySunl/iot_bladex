package org.springblade.modules.iot.broker.mqtt.service;

import org.springblade.basic.base.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;
import org.springblade.modules.iot.vo.result.MqttSessionDetailsResultVO;

/**
 * MqttBroker API
 *
 * <p>broker 协议接入层 ── MQTT 协议出口。包路径 {@code broker.mqtt.service}
 * 与 {@code broker.ws.service} / {@code broker.common.*} 同级,按协议拆分,便于扩展。
 *
 * @author ShiHuan Sun
 * @email 13733918655@163.com
 */
public interface MqttBrokerService {


    /**
     * Publishes a message to a specified topic and returns the content if successful.
     *
     * @param publishMessageRequestVO Object containing the required parameters for publishing.
     * @return The content of the published message.
     * @throws ServiceException If the publishing fails.
     */
    String publishMessage(PublishMessageRequestVO publishMessageRequestVO);


    /**
     * Retrieves session information from the MQTT broker.
     *
     * @param tenantId The tenant identifier under which the session is registered.
     * @param userId   The unique identifier of the user who established the session.
     * @param clientId The unique client identifier of the MQTT session.
     * @return {@link MqttSessionDetailsResultVO} The session details as a entity.
     * @throws Exception If an error occurs while retrieving session information.
     */
    MqttSessionDetailsResultVO getSessionInfo(String tenantId, String userId, String clientId) throws Exception;

    /**
     * 查询设备 BifroMQ session 实时在线状态(三态语义).
     *
     * @param tenantId             租户 ID
     * @param deviceIdentification 设备标识(作为 BifroMQ userId)
     * @param clientId             MQTT clientId
     * @return {@link R#success(Object)} {@code (true)} 在线;{@link R#success(Object)} {@code (false)} 离线(broker 404);
     *         {@link R#fail()} 不确定(broker 临时异常 / 超时,调用方应保留现状)
     */
    R<Boolean> isOnline(String tenantId, String deviceIdentification, String clientId);

    /**
     * Expires inactive persistent sessions for a specified tenant.
     *
     * @param tenantId      The identifier of the tenant for which sessions may be expired.
     * @param expirySeconds The time in seconds after which the session should be considered inactive and expired.
     * @throws ServiceException If an error occurs while expiring sessions.
     */
    void expireSession(String tenantId, String expirySeconds) throws ServiceException;

    /**
     * Disconnects an MQTT client connection based on the provided parameters.
     *
     * @param tenantId   The tenant identifier.
     * @param userId     The user identifier of the MQTT client connection to be disconnected.
     * @param clientId   The client identifier of the MQTT client connection to be disconnected.
     * @param clientType The type of client.
     * @throws ServiceException If an error occurs while disconnecting the client.
     */
    void killClientConnection(String tenantId, String userId, String clientId, String clientType) throws ServiceException;

    /**
     * Adds a topic subscription to an MQTT session.
     *
     * @param tenantId    The tenant identifier.
     * @param userId      The user identifier who established the session.
     * @param clientId    The client identifier of the MQTT session.
     * @param topicFilter The topic filter for the subscription.
     * @param subQos      The QoS level of the subscription.
     * @throws ServiceException If an error occurs while adding the subscription.
     */
    void addTopicSubscription(String tenantId, String userId, String clientId, String topicFilter, String subQos) throws ServiceException;

    /**
     * Removes a topic subscription from an MQTT session.
     *
     * @param tenantId    The tenant identifier.
     * @param userId      The user identifier who established the session.
     * @param clientId    The client identifier of the MQTT session.
     * @param topicFilter The topic filter for the subscription.
     * @throws ServiceException If an error occurs while removing the subscription.
     */
    void removeTopicSubscription(String tenantId, String userId, String clientId, String topicFilter) throws ServiceException;
}
