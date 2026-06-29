package org.springblade.modules.iot.zlm.event;

import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.hook.OnStreamChangedHookParam;

/**
 * 流离开事件
 */
public class MediaDepartureEvent extends MediaEvent {
    public MediaDepartureEvent(Object source) {
        super(source);
    }

    public static MediaDepartureEvent getInstance(Object source, OnStreamChangedHookParam hookParam, ZlmMediaServer mediaServer) {
        MediaDepartureEvent mediaDepartureEven = new MediaDepartureEvent(source);
        mediaDepartureEven.setApp(hookParam.getApp());
        mediaDepartureEven.setStream(hookParam.getStream());
        mediaDepartureEven.setSchema(hookParam.getSchema());
        mediaDepartureEven.setMediaServer(mediaServer);
        return mediaDepartureEven;
    }
}
