package org.springblade.modules.iot.onvif.requests;

import org.springblade.modules.iot.onvif.listeners.OnvifResponseListener;
import org.springblade.modules.iot.onvif.models.OnvifType;

/**
 * ONVIF 云台停止请求
 *
 * @FileName StopRequest
 * @Description
 **/
public class StopRequest implements OnvifRequest {

    private final String profileToken;
    private final boolean panTilt;
    private final boolean zoom;
    private final OnvifResponseListener listener;

    public StopRequest(String profileToken, boolean panTilt, boolean zoom, OnvifResponseListener listener) {
        this.profileToken = profileToken;
        this.panTilt = panTilt;
        this.zoom = zoom;
        this.listener = listener;
    }

    public OnvifResponseListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<Stop xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + profileToken + "</ProfileToken>" +
                "<PanTilt>" + panTilt + "</PanTilt>" +
                "<Zoom>" + zoom + "</Zoom>" +
                "</Stop>";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.STOP;
    }
}
