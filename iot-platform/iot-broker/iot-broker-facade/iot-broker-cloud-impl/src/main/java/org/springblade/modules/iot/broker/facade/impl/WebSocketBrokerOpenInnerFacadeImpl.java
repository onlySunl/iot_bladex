package org.springblade.modules.iot.broker.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.broker.WebSocketBrokerOpenInnerFacade;
import org.springblade.modules.iot.broker.api.WebSocketBrokerOpenInnerApi;
import org.springblade.modules.iot.vo.query.PublishWebSocketMessageRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/12/24 15:54
 */
@Service
public class WebSocketBrokerOpenInnerFacadeImpl implements WebSocketBrokerOpenInnerFacade {
    @Autowired
    @Lazy
    private WebSocketBrokerOpenInnerApi webSocketBrokerOpenInnerApi;

    @Override
    public R<?> sendMessage(PublishWebSocketMessageRequestVO publishMessageRequestVO) {
        return webSocketBrokerOpenInnerApi.sendMessage(publishMessageRequestVO);
    }
}
