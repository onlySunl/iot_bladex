package org.springblade.modules.iot.bean;

import lombok.Data;

@Data
public class DeviceNotFoundEvent {

    private String callId;

    public DeviceNotFoundEvent(String callId) {
        this.callId = callId;
    }
}
