package org.springblade.modules.nvr.hook.event;

import org.springblade.modules.nvr.domain.ZlmMediaServer;
import org.springframework.context.ApplicationEvent;

/**
 * zlm server_start事件
 */
public class HookZlmServerStartEvent extends ApplicationEvent {

    public HookZlmServerStartEvent(Object source) {
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
