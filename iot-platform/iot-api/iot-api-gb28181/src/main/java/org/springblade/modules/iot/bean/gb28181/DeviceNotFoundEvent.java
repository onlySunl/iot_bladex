package org.springblade.modules.iot.bean.gb28181;

import lombok.Data;

@Data
public class DeviceNotFoundEvent {

    private String callId;

    public DeviceNotFoundEvent(String callId) {
        this.callId = callId;
    }
}
