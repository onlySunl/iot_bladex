package org.springblade.modules.iot.domain;

import java.util.List;

public class CatalogRequest {
    private Gb28181Platform platform;

    public Gb28181Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Gb28181Platform platform) {
        this.platform = platform;
    }
}
