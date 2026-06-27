package org.springblade.modules.iot.domain.gb28181;

import org.springblade.modules.iot.domain.SimpleDeviceInfo;

import java.util.List;

public class CatalogRequest {
    private Gb28181Platform platform;
    private List<SimpleDeviceInfo> deviceList;

    public Gb28181Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Gb28181Platform platform) {
        this.platform = platform;
    }

    public List<SimpleDeviceInfo> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<SimpleDeviceInfo> deviceList) {
        this.deviceList = deviceList;
    }
}
