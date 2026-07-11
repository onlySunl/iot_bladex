package org.springblade.modules.nvr.onvif.requests;

import org.springblade.modules.nvr.onvif.listeners.NetworkInfoListener;
import org.springblade.modules.nvr.onvif.models.OnvifType;

/**
 * 获取网络接口配置请求
 */
public class GetNetworkInterfacesRequest implements OnvifRequest {
    public static final String TAG = GetNetworkInterfacesRequest.class.getSimpleName();
    private final NetworkInfoListener listener;

    public GetNetworkInterfacesRequest(NetworkInfoListener listener) {
        this.listener = listener;
    }

    public NetworkInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetNetworkInterfaces xmlns=\"http://www.onvif.org/ver10/device/wsdl\"/>";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_NETWORK_INTERFACES;
    }
}

