package org.springblade.modules.nvr.domain;

import lombok.Data;


@Data
public class WSOnvifDevice {
    private String ip;
    private String auth;
    private String hostName;
    private String username;
    private String password;
}
