package org.springblade.modules.nvr.onvif.requests;

import org.springblade.modules.nvr.onvif.listeners.MediaInfoListener;
import org.springblade.modules.nvr.onvif.models.OnvifType;

/**
 * 获取音频源配置请求
 */
public class GetAudioSourceConfigsRequest implements OnvifRequest {
    public static final String TAG = GetAudioSourceConfigsRequest.class.getSimpleName();
    private final MediaInfoListener listener;

    public GetAudioSourceConfigsRequest(MediaInfoListener listener) {
        this.listener = listener;
    }

    public MediaInfoListener getListener() {
        return listener;
    }

    @Override
    public String getXml() {
        return "<GetAudioSourceConfigurations xmlns=\"http://www.onvif.org/ver10/media/wsdl\"/>";
    }

    @Override
    public OnvifType getType() {
        return OnvifType.GET_AUDIO_SOURCE_CONFIGS;
    }
}

