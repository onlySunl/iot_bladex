package org.springblade.modules.iot.broker;

import com.alibaba.fastjson2.JSON;
import org.springblade.basic.base.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.broker.ws.service.WebSocketBrokerService;
import org.springblade.modules.iot.vo.query.PublishWebSocketMessageRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tangyh
 * @since 2024/12/24 15:54
 */
@Service
@Slf4j
public class WebSocketBrokerOpenInnerFacadeImpl implements WebSocketBrokerOpenInnerFacade {
    @Autowired
    private WebSocketBrokerService webSocketBrokerService;

    @Override
    public R<?> sendMessage(PublishWebSocketMessageRequestVO publishMessageRequestVO) {
        log.info("Received request to send message.param {}", JSON.toJSONString(publishMessageRequestVO));
        try {
            return R.success(webSocketBrokerService.publishMessage(publishMessageRequestVO));
        } catch (ServiceException e) {
            log.error("Failed to send message. param: {}", JSON.toJSONString(publishMessageRequestVO), e);
            return R.fail(e.getMessage());
        }
    }
}
