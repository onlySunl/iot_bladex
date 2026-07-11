package org.springblade.modules.nvr.mediaServer;

import org.springblade.modules.nvr.domain.ZlmMediaServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;


public abstract class MediaServerEventAbstract extends ApplicationEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private ZlmMediaServer mediaServer;


    public MediaServerEventAbstract(Object source) {
        super(source);
    }

}
