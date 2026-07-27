package org.springblade.common.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 模板包装类
 */
@Component
public class RocketmqTemplate {
    
    /**
     * 同步发送消息
     */
    public void syncSend(String destination, Object payload) {
        // TODO: 实现 RocketMQ 发送逻辑
    }
    
    /**
     * 异步发送消息
     */
    public void asyncSend(String destination, Object payload) {
        // TODO: 实现 RocketMQ 异步发送逻辑
    }
}
