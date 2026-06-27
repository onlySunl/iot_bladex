package org.springblade.modules.iot.onvif.requests;

import org.springblade.modules.iot.onvif.listeners.NetworkInfoListener;
import org.springblade.modules.iot.onvif.models.OnvifType;

/**
 * 获取网络协议配置请求
 */
public class GetNetworkProtocolsRequest implements OnvifRequest {
    public static final String TAG = GetNetworkProtocolsRequest.class.getSimpleName();
    private final NetworkInfoListener listener;

    public GetNetworkProtocolsRequest(NetworkInfoListener listener) {
        this.listener = listener;
    }

    public NetworkInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetNetworkProtocols xmlns=\"http://www.onvif.org/ver10/device/wsdl\"/>";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_NETWORK_PROTOCOLS;
    }
}

