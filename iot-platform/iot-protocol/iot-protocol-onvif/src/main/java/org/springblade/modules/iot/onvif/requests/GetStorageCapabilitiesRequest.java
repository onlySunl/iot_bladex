package org.springblade.modules.iot.onvif.requests;

import org.springblade.modules.iot.onvif.listeners.StorageInfoListener;
import org.springblade.modules.iot.onvif.models.OnvifType;

/**
 * 获取存储能力请求
 */
public class GetStorageCapabilitiesRequest implements OnvifRequest {

    public static final String TAG = GetStorageCapabilitiesRequest.class.getSimpleName();

    private final StorageInfoListener listener;

    public GetStorageCapabilitiesRequest(StorageInfoListener listener) {
        this.listener = listener;
    }

    public StorageInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetStorageCapabilities xmlns=\"http://www.onvif.org/ver10/device/wsdl\" />";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_STORAGE_CAPABILITIES;
    }
}
