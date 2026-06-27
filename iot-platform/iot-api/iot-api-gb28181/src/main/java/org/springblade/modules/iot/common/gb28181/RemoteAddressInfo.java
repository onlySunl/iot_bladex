package org.springblade.modules.iot.common.gb28181;

public class RemoteAddressInfo {
    private String ip;
    private int port;

    public RemoteAddressInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
