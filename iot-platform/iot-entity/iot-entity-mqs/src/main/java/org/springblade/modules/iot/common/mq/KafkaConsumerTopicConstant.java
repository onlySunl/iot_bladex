package org.springblade.modules.iot.common.mq;

/**
 * MQ(Topic 命名集中表(Stub)。
 * 原Thinglinks: topic常量全部集中在此接口,含MQTT/WS/TCP 三个协议的完整 topic。
 */
public interface KafkaConsumerTopicConstant {

    interface MqsWebSocket {
        String THINGLINKS_WEBSOCKET_CLIENT_CONNECTED_TOPIC        = "thinglinks.websocket.client.connected.topic";
        String THINGLINKS_WEBSOCKET_CLIENT_DISCONNECTED_TOPIC     = "thinglinks.websocket.client.disconnected.topic";
        String THINGLINKS_WEBSOCKET_SERVER_DISCONNECTED_TOPIC     = "thinglinks.websocket.server.disconnected.topic";
        String THINGLINKS_WEBSOCKET_DEVICE_KICKED_TOPIC           = "thinglinks.websocket.device.kicked.topic";
        String THINGLINKS_WEBSOCKET_DISTRIBUTION_ERROR_TOPIC      = "thinglinks.websocket.distribution.error.topic";
        String THINGLINKS_WEBSOCKET_DISTRIBUTION_COMPLETED_TOPIC   = "thinglinks.websocket.distribution.completed.topic";
        String THINGLINKS_WEBSOCKET_PING_REQ_TOPIC                = "thinglinks.websocket.ping.req.topic";
    }

    interface MqsMqtt {
        String THINGLINKS_MQTT_CLIENT_CONNECTED_TOPIC        = "thinglinks.mqtt.client.connected.topic";
        String THINGLINKS_MQTT_CLIENT_DISCONNECTED_TOPIC     = "thinglinks.mqtt.client.disconnected.topic";
        String THINGLINKS_MQTT_SERVER_CONNECTED_TOPIC        = "thinglinks.mqtt.server.connected.topic";
        String THINGLINKS_MQTT_DEVICE_KICKED_TOPIC           = "thinglinks.mqtt.device.kicked.topic";
        String THINGLINKS_MQTT_SUBSCRIPTION_ACKED_TOPIC      = "thinglinks.mqtt.subscription.acked.topic";
        String THINGLINKS_MQTT_UNSUBSCRIPTION_ACKED_TOPIC    = "thinglinks.mqtt.unsubscription.acked.topic";
        String THINGLINKS_MQTT_DISTRIBUTION_ERROR_TOPIC      = "thinglinks.mqtt.distribution.error.topic";
        String THINGLINKS_MQTT_DISTRIBUTION_COMPLETED_TOPIC   = "thinglinks.mqtt.distribution.completed.topic";
        String THINGLINKS_MQTT_PING_REQ_TOPIC                = "thinglinks.mqtt.ping.req.topic";
        String THINGLINKS_MQTT_SESSION_START_TOPIC           = "thinglinks.mqtt.session.start.topic";
        String THINGLINKS_MQTT_SESSION_STOP_TOPIC            = "thinglinks.mqtt.session.stop.topic";
        String THINGLINKS_MQTT_CLIENT_UNAUTHORIZED_TOPIC     = "thinglinks.mqtt.client.unauthorized.topic";
    }

    interface MqsTcp {
        String THINGLINKS_TCP_CLIENT_CONNECTED_TOPIC        = "thinglinks.tcp.client.connected.topic";
        String THINGLINKS_TCP_CLIENT_DISCONNECTED_TOPIC     = "thinglinks.tcp.client.disconnected.topic";
        String THINGLINKS_TCP_SERVER_DISCONNECTED_TOPIC     = "thinglinks.tcp.server.disconnected.topic";
        String THINGLINKS_TCP_DEVICE_KICKED_TOPIC           = "thinglinks.tcp.device.kicked.topic";
        String THINGLINKS_TCP_SUBSCRIPTION_ACKED_TOPIC      = "thinglinks.tcp.subscription.acked.topic";
        String THINGLINKS_TCP_UNSUBSCRIPTION_ACKED_TOPIC    = "thinglinks.tcp.unsubscription.acked.topic";
        String THINGLINKS_TCP_DISTRIBUTION_ERROR_TOPIC      = "thinglinks.tcp.distribution.error.topic";
        String THINGLINKS_TCP_DISTRIBUTION_COMPLETED_TOPIC   = "thinglinks.tcp.distribution.completed.topic";
        String THINGLINKS_TCP_PING_REQ_TOPIC                = "thinglinks.tcp.ping.req.topic";
    }
}
