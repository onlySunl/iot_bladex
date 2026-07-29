package org.springblade.modules.iot.common.mq;

/**
 * MQ(Topic 命名集中表(Stub)。
 * 原Thinglinks: topic常量全部集中在此接口,含MQTT/WS/TCP 三个协议的完整 topic。
 */
public interface KafkaConsumerTopicConstant {

    interface MqsWebSocket {
        String IOT_WEBSOCKET_CLIENT_CONNECTED_TOPIC        = "iot.websocket.client.connected.topic";
        String IOT_WEBSOCKET_CLIENT_DISCONNECTED_TOPIC     = "iot.websocket.client.disconnected.topic";
        String IOT_WEBSOCKET_SERVER_DISCONNECTED_TOPIC     = "iot.websocket.server.disconnected.topic";
        String IOT_WEBSOCKET_DEVICE_KICKED_TOPIC           = "iot.websocket.device.kicked.topic";
        String IOT_WEBSOCKET_DISTRIBUTION_ERROR_TOPIC      = "iot.websocket.distribution.error.topic";
        String IOT_WEBSOCKET_DISTRIBUTION_COMPLETED_TOPIC   = "iot.websocket.distribution.completed.topic";
        String IOT_WEBSOCKET_PING_REQ_TOPIC                = "iot.websocket.ping.req.topic";
    }

    interface MqsMqtt {
        String IOT_MQTT_CLIENT_CONNECTED_TOPIC        = "iot.mqtt.client.connected.topic";
        String IOT_MQTT_CLIENT_DISCONNECTED_TOPIC     = "iot.mqtt.client.disconnected.topic";
        String IOT_MQTT_SERVER_CONNECTED_TOPIC        = "iot.mqtt.server.connected.topic";
        String IOT_MQTT_DEVICE_KICKED_TOPIC           = "iot.mqtt.device.kicked.topic";
        String IOT_MQTT_SUBSCRIPTION_ACKED_TOPIC      = "iot.mqtt.subscription.acked.topic";
        String IOT_MQTT_UNSUBSCRIPTION_ACKED_TOPIC    = "iot.mqtt.unsubscription.acked.topic";
        String IOT_MQTT_DISTRIBUTION_ERROR_TOPIC      = "iot.mqtt.distribution.error.topic";
        String IOT_MQTT_DISTRIBUTION_COMPLETED_TOPIC   = "iot.mqtt.distribution.completed.topic";
        String IOT_MQTT_PING_REQ_TOPIC                = "iot.mqtt.ping.req.topic";
        String IOT_MQTT_SESSION_START_TOPIC           = "iot.mqtt.session.start.topic";
        String IOT_MQTT_SESSION_STOP_TOPIC            = "iot.mqtt.session.stop.topic";
        String IOT_MQTT_CLIENT_UNAUTHORIZED_TOPIC     = "iot.mqtt.client.unauthorized.topic";
    }

    interface MqsTcp {
        String IOT_TCP_CLIENT_CONNECTED_TOPIC        = "iot.tcp.client.connected.topic";
        String IOT_TCP_CLIENT_DISCONNECTED_TOPIC     = "iot.tcp.client.disconnected.topic";
        String IOT_TCP_SERVER_DISCONNECTED_TOPIC     = "iot.tcp.server.disconnected.topic";
        String IOT_TCP_DEVICE_KICKED_TOPIC           = "iot.tcp.device.kicked.topic";
        String IOT_TCP_SUBSCRIPTION_ACKED_TOPIC      = "iot.tcp.subscription.acked.topic";
        String IOT_TCP_UNSUBSCRIPTION_ACKED_TOPIC    = "iot.tcp.unsubscription.acked.topic";
        String IOT_TCP_DISTRIBUTION_ERROR_TOPIC      = "iot.tcp.distribution.error.topic";
        String IOT_TCP_DISTRIBUTION_COMPLETED_TOPIC   = "iot.tcp.distribution.completed.topic";
        String IOT_TCP_PING_REQ_TOPIC                = "iot.tcp.ping.req.topic";
    }
}
