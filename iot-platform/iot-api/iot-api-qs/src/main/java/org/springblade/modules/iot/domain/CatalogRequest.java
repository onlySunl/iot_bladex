package org.springblade.modules.iot.domain;


import java.util.List;

public class CatalogRequest {
    private List<SimpleDeviceInfo> deviceList;

    public List<SimpleDeviceInfo> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<SimpleDeviceInfo> deviceList) {
        this.deviceList = deviceList;
    }
}
