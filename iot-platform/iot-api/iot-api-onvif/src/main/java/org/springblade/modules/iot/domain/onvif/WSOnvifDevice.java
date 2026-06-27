package org.springblade.modules.iot.domain.onvif;

import lombok.Data;


@Data
public class WSOnvifDevice {
    private String ip;
    private String auth;
    private String hostName;
    private String username;
    private String password;
}
