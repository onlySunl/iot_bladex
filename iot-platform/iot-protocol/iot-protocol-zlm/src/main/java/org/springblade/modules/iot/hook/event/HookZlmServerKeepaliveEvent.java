package org.springblade.modules.iot.hook.event;

import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springframework.context.ApplicationEvent;

/**
 * zlm 心跳事件
 */
public class HookZlmServerKeepaliveEvent extends ApplicationEvent {

    public HookZlmServerKeepaliveEvent(Object source) {
        super(source);
    }

    private ZlmMediaServer mediaServerItem;

    public ZlmMediaServer getMediaServerItem() {
        return mediaServerItem;
    }

    public void setMediaServerItem(ZlmMediaServer mediaServerItem) {
        this.mediaServerItem = mediaServerItem;
    }
}
