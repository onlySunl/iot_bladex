package org.springblade.modules.nvr.mediaServer;

import org.springblade.modules.nvr.domain.ZlmMediaServer;
import org.springblade.modules.nvr.event.MediaEvent;
import org.springblade.modules.nvr.hook.ABLHookParam;
import org.springblade.modules.nvr.hook.OnStreamNotFoundHookParam;

/**
 * 流未找到
 */
public class MediaNotFoundEvent extends MediaEvent {
    public MediaNotFoundEvent(Object source) {
        super(source);
    }

    public static MediaNotFoundEvent getInstance(Object source, OnStreamNotFoundHookParam hookParam, ZlmMediaServer mediaServer) {
        MediaNotFoundEvent mediaDepartureEven = new MediaNotFoundEvent(source);
        mediaDepartureEven.setApp(hookParam.getApp());
        mediaDepartureEven.setStream(hookParam.getStream());
        mediaDepartureEven.setSchema(hookParam.getSchema());
        mediaDepartureEven.setMediaServer(mediaServer);
        mediaDepartureEven.setParams(hookParam.getParams());
        return mediaDepartureEven;
    }

    public static MediaNotFoundEvent getInstance(Object source, ABLHookParam hookParam, ZlmMediaServer mediaServer) {
        MediaNotFoundEvent mediaDepartureEven = new MediaNotFoundEvent(source);
        mediaDepartureEven.setApp(hookParam.getApp());
        mediaDepartureEven.setStream(hookParam.getStream());
        mediaDepartureEven.setMediaServer(mediaServer);
        return mediaDepartureEven;
    }
}
