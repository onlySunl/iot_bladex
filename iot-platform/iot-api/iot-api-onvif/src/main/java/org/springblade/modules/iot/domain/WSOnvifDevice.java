package org.springblade.modules.iot.domain;

import lombok.Data;


@Data
public class WSOnvifDevice {
    private String ip;
    private String auth;
    private String hostName;
    private String username;
    private String password;
}
