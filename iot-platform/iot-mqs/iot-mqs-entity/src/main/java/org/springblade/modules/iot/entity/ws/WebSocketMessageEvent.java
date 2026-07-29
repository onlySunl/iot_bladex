package org.springblade.modules.iot.entity.ws;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @program: iot-platform
 * @description: WebSocketMessageEvent
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-04-28 14:42
 **/
@Getter
public class WebSocketMessageEvent extends ApplicationEvent {
    private final String topic;
    private final String qos;
    private final String message;
    private final String time;

    public WebSocketMessageEvent(Object source, String topic, String qos, String message, String time) {
        super(source);
        this.topic = topic;
        this.qos = qos;
        this.message = message;
        this.time = time;
    }


    public class AddSubDeviceEvent extends WebSocketMessageEvent {
        public AddSubDeviceEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class AddSubDeviceResponseEvent extends WebSocketMessageEvent {
        public AddSubDeviceResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceStatusEvent extends WebSocketMessageEvent {
        public DeviceStatusEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceStatusResponseEvent extends WebSocketMessageEvent {
        public DeviceStatusResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceControlEvent extends WebSocketMessageEvent {
        public DeviceControlEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceControlResponseEvent extends WebSocketMessageEvent {
        public DeviceControlResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceDataReportEvent extends WebSocketMessageEvent {
        public DeviceDataReportEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceDataReportResponseEvent extends WebSocketMessageEvent {
        public DeviceDataReportResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }
}
