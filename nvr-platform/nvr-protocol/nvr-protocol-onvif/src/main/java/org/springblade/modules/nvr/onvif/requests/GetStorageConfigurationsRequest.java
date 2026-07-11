package org.springblade.modules.nvr.onvif.requests;

import org.springblade.modules.nvr.onvif.listeners.StorageInfoListener;
import org.springblade.modules.nvr.onvif.models.OnvifType;

/**
 * 获取存储配置请求
 */
public class GetStorageConfigurationsRequest implements OnvifRequest {

    public static final String TAG = GetStorageConfigurationsRequest.class.getSimpleName();

    private final StorageInfoListener listener;

    public GetStorageConfigurationsRequest(StorageInfoListener listener) {
        this.listener = listener;
    }

    public StorageInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetStorageConfigurations xmlns=\"http://www.onvif.org/ver10/device/wsdl\" />";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_STORAGE_CONFIGURATIONS;
    }
}
