package org.springblade.modules.nvr.hook.event;

import org.springblade.modules.nvr.domain.ZlmMediaServer;
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
