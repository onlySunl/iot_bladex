package org.springblade.modules.iot.entity.tcp;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @program: iot-platform
 * @description: TcpMessageEvent
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-04-28 14:42
 **/
@Getter
public class TcpMessageEvent extends ApplicationEvent {
    private final String topic;
    private final String qos;
    private final String message;
    private final String time;

    public TcpMessageEvent(Object source, String topic, String qos, String message, String time) {
        super(source);
        this.topic = topic;
        this.qos = qos;
        this.message = message;
        this.time = time;
    }


    public class AddSubDeviceEvent extends TcpMessageEvent {
        public AddSubDeviceEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class AddSubDeviceResponseEvent extends TcpMessageEvent {
        public AddSubDeviceResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceStatusEvent extends TcpMessageEvent {
        public DeviceStatusEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceStatusResponseEvent extends TcpMessageEvent {
        public DeviceStatusResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceControlEvent extends TcpMessageEvent {
        public DeviceControlEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceControlResponseEvent extends TcpMessageEvent {
        public DeviceControlResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceDataReportEvent extends TcpMessageEvent {
        public DeviceDataReportEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }

    public class DeviceDataReportResponseEvent extends TcpMessageEvent {
        public DeviceDataReportResponseEvent(Object source, String topic, String qos, String body, String time) {
            super(source, topic, qos, body, time);
        }
    }
}
