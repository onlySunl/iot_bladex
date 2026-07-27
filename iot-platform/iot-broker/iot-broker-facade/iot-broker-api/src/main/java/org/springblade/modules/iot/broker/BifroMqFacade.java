package org.springblade.modules.iot.broker;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * -----------------------------------------------------------------------------
 * File Name: BifroMQApi.java
 * -----------------------------------------------------------------------------
 * Description:
 * BifroMQ  Api
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
 * @date 2023-10-31 12:20
 */
public interface BifroMqFacade {

    /**
     * Publish a message to a given topic.
     *
     * @param reqId         Optional caller provided request id, expected as a Long.
     * @param tenantId      The tenant ID, required.
     * @param topic         The message topic, required.
     * @param qos           QoS of the message to be published, required.
     * @param expirySeconds The message expiry seconds, optional.
     * @param clientType    The publisher type, required.
     * @param payload       Message payload will be treated as binary, required.
     * @return ResponseEntity indicating success or failure.
     */
    ResponseEntity<String> publishMessage(
            Long reqId,
            String tenantId,
            String topic,
            String qos,
            String expirySeconds,
            String clientType,
            byte[] payload
    );

    /**
     * Posts a message to a specific topic to be retained or sends zero bytes to clear the retained message.
     * This allows setting or clearing a message retained on a MQTT broker, which will be sent to new subscribers immediately upon subscription.
     *
     * @param reqId          Optional request ID provided by the caller, for tracking purposes.
     * @param tenantId       The identifier of the tenant under which the message is published.
     * @param topic          The MQTT topic to which the message will be published.
     * @param qos            Quality of Service level for the MQTT message retention.
     * @param expirySeconds  The duration in seconds after which the retained message should expire, optional.
     * @param clientType     The type of the client that is publishing the message.
     * @param clientMetadata Custom metadata about the publisher, prefixed with 'client_meta_', handled dynamically.
     * @param payload        The binary content of the message to be retained; zero bytes to clear retention.
     * @return ResponseEntity with the status of the operation.
     */
    ResponseEntity<String> retainMessage(
            Long reqId,
            String tenantId,
            String topic,
            String qos,
            String expirySeconds,
            String clientType,
            Map<String, String> clientMetadata,
            String payload
    );

    /**
     * Retrieves session information for a specified user and client ID from the MQTT broker.
     * This is useful for system administrators to monitor or manage MQTT session states.
     *
     * @param reqId    Optional request ID provided by the caller, useful for tracing requests.
     * @param tenantId The tenant identifier under which the session is registered.
     * @param userId   The unique identifier of the user who established the session.
     * @param clientId The unique client identifier of the MQTT session.
     * @return ResponseEntity containing the session details or an error message if not found.
     */
    ResponseEntity<String> getSessionInfo(
            Long reqId,
            String tenantId,
            String userId,
            String clientId
    );

    /**
     * Manually expires inactive persistent sessions for a specified tenant using an overridden expiry time.
     * Setting the expiry_seconds to zero will clear all inboxes under this tenant, potentially disconnecting live sessions.
     *
     * @param reqId         Optional request ID provided by the caller for tracing.
     * @param tenantId      The identifier of the tenant for which sessions may be expired.
     * @param expirySeconds The time in seconds after which the session should be considered inactive and expired. If set to zero, all sessions will be cleared.
     * @return ResponseEntity indicating whether the operation was successful or failed.
     */
    ResponseEntity<String> expireSession(
            Long reqId,
            String tenantId,
            String expirySeconds
    );


    /**
     * Disconnects a MQTT client connection based on the provided parameters.
     *
     * @param reqId          Optional caller provided request id.
     * @param tenantId       The tenant id.
     * @param userId         The user id of the MQTT client connection to be disconnected.
     * @param clientId       The client id of the MQTT client connection to be disconnected.
     * @param clientType     The client type.
     * @param clientMetadata Metadata header about the kicker client, must start with client_meta_.
     * @return ResponseEntity indicating the result of the operation.
     */
    ResponseEntity<String> killClientConnection(
            Long reqId,
            String tenantId,
            String userId,
            String clientId,
            String clientType,
            Map<String, String> clientMetadata
    );

    /**
     * Adds a topic subscription to an MQTT session.
     *
     * @param reqId       Optional caller provided request id.
     * @param tenantId    The tenant id.
     * @param userId      The id of user who established the session.
     * @param clientId    The client id of the MQTT session.
     * @param topicFilter The topic filter to add.
     * @param subQos      The QoS of the subscription.
     * @return ResponseEntity indicating the result of the operation.
     */
    ResponseEntity<String> addTopicSubscription(
            Long reqId,
            String tenantId,
            String userId,
            String clientId,
            String topicFilter,
            String subQos
    );

    /**
     * Removes a topic subscription from an inbox.
     *
     * @param reqId       Optional caller provided request id.
     * @param tenantId    The tenant id.
     * @param userId      The id of user who established the session.
     * @param clientId    The client id of the MQTT session.
     * @param topicFilter The topic filter to add.
     * @return ResponseEntity indicating the result of the operation.
     */
    ResponseEntity<String> removeTopicSubscription(
            Long reqId,
            String tenantId,
            String userId,
            String clientId,
            String topicFilter
    );

}
