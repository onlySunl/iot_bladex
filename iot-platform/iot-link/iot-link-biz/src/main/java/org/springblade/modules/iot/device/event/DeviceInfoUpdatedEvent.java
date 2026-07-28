package org.springblade.modules.iot.device.event;

import org.springblade.modules.iot.device.event.source.DeviceInfoUpdatedEventSource;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Description:
 * 设备信息更新事件
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/5
 */
@Getter
public class DeviceInfoUpdatedEvent extends ApplicationEvent {
    private final DeviceInfoUpdatedEventSource eventSource;

    public DeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource source) {
        super(source);
        this.eventSource = source;
    }
}
