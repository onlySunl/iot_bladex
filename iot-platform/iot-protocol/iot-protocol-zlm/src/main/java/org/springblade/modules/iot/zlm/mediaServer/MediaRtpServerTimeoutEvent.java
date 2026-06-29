package org.springblade.modules.iot.zlm.mediaServer;

import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.zlm.hook.OnStreamChangedHookParam;
import org.springblade.modules.iot.zlm.event.MediaEvent;

/**
 * RtpServer收流超时事件
 */
public class MediaRtpServerTimeoutEvent extends MediaEvent {
    public MediaRtpServerTimeoutEvent(Object source) {
        super(source);
    }

    public static MediaRtpServerTimeoutEvent getInstance(Object source, OnStreamChangedHookParam hookParam, ZlmMediaServer mediaServer) {
        MediaRtpServerTimeoutEvent mediaDepartureEven = new MediaRtpServerTimeoutEvent(source);
        mediaDepartureEven.setApp(hookParam.getApp());
        mediaDepartureEven.setStream(hookParam.getStream());
        mediaDepartureEven.setSchema(hookParam.getSchema());
        mediaDepartureEven.setMediaServer(mediaServer);
        return mediaDepartureEven;
    }
}
