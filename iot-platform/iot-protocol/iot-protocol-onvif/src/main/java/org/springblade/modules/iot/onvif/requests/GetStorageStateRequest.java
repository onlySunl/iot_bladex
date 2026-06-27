package org.springblade.modules.iot.onvif.requests;

import org.springblade.modules.iot.onvif.listeners.StorageInfoListener;
import org.springblade.modules.iot.onvif.models.OnvifType;

/**
 * 获取存储状态请求
 */
public class GetStorageStateRequest implements OnvifRequest {

    public static final String TAG = GetStorageStateRequest.class.getSimpleName();

    private final StorageInfoListener listener;

    public GetStorageStateRequest(StorageInfoListener listener) {
        this.listener = listener;
    }

    public StorageInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetStorageState xmlns=\"http://www.onvif.org/ver10/device/wsdl\" />";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_STORAGE_STATE;
    }
}
