package org.springblade.modules.iot.onvif.requests;

import org.springblade.modules.iot.onvif.listeners.OnvifResponseListener;
import org.springblade.modules.iot.onvif.models.OnvifType;

/**
 * ONVIF 获取预置点列表请求
 *
 * @FileName GetPresetsRequest
 * @Description
 **/
public class GetPresetsRequest implements OnvifRequest {

    private final String profileToken;
    private final OnvifResponseListener listener;

    public GetPresetsRequest(String profileToken, OnvifResponseListener listener) {
        this.profileToken = profileToken;
        this.listener = listener;
    }

    public OnvifResponseListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetPresets xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + profileToken + "</ProfileToken>" +
                "</GetPresets>";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_PRESETS;
    }
}
