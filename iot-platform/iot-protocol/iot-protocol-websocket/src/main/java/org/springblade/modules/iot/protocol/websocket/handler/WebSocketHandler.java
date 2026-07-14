

package org.springblade.modules.iot.protocol.websocket.handler;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springblade.modules.iot.protocol.websocket.entity.WebSocketSession;
import org.springblade.modules.iot.protocol.websocket.entity.WebSocketUPRequest;
import org.springblade.modules.iot.protocol.websocket.enums.WebSocketMessageType;
import org.springblade.modules.iot.protocol.websocket.enums.WebSocketStatus;
import org.springblade.modules.iot.protocol.websocket.handle.WebSocketUPHandle;
import org.springblade.modules.iot.protocol.websocket.processor.up.WebSocketUPProcessorChain;
import org.springblade.modules.iot.protocol.websocket.service.WebSocketSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 消息处理器
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2026/1/13
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private WebSocketSessionManager sessionManager;

    @Autowired
    private WebSocketUPHandle upHandle;

    @Autowired
    private WebSocketUPProcessorChain upProcessorChain;

    /**
     * 连接建立后
     */
    @Override
    public void afterConnectionEstablished(org.springframework.web.socket.WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        log.info("[WebSocket连接][建立成功] SessionID: {}, RemoteAddress: {}", 
                sessionId, session.getRemoteAddress());

        // 创建会话对象
        WebSocketSession wsSession = WebSocketSession.builder()
                .sessionId(sessionId)
                .remoteAddress(session.getRemoteAddress() != null ? 
                        session.getRemoteAddress().toString() : "unknown")
                .status(WebSocketStatus.CONNECTED)
                .connectTime(LocalDateTime.now())
                .lastActiveTime(LocalDateTime.now())
                .build();

        // 添加到会话管理器
        sessionManager.addSession(session);

        // 发送欢迎消息（可选）
        session.sendMessage(new TextMessage("Welcome to WebSocket IoT Platform"));
    }

    /**
     * 接收到消息
     */
    @Override
    protected void handleTextMessage(org.springframework.web.socket.WebSocketSession session, 
                                       TextMessage message) throws Exception {
        String sessionId = session.getId();
        String payload = message.getPayload();
        
        log.info("[WebSocket] 收到消息 - SessionID: {}, Payload长度: {}, Payload: {}", 
                sessionId, payload.length(), payload);

        try {
            // 更新最后活跃时间
            sessionManager.updateLastActiveTime(sessionId);

            // 构建上行请求
            WebSocketUPRequest upRequest = WebSocketUPRequest.builder()
                    .sessionId(sessionId)
                    .wsMessageType(WebSocketMessageType.TEXT)
                    .payload(payload)
                    .time(System.currentTimeMillis())
                    .build();

            // 执行上行处理器链（认证、元数据提取、编解码、事件生成）
            upProcessorChain.process(upRequest);

            // 推送到IoT平台
            upHandle.up(Collections.singletonList(upRequest));

        } catch (Exception e) {
            log.error("[WebSocket消息][处理异常] SessionID: {}", sessionId, e);
            session.sendMessage(new TextMessage("{\"error\":\"Message processing failed\"}"));
        }
    }

    /**
     * 处理传输错误
     */
    @Override
    public void handleTransportError(org.springframework.web.socket.WebSocketSession session, 
                                       Throwable exception) throws Exception {
        String sessionId = session.getId();
        log.error("[WebSocket错误][传输异常] SessionID: {}", sessionId, exception);
        
        // 关闭会话
        sessionManager.closeSession(sessionId);
    }

    /**
     * 连接关闭后
     */
    @Override
    public void afterConnectionClosed(org.springframework.web.socket.WebSocketSession session, 
                                        CloseStatus status) throws Exception {
        String sessionId = session.getId();
        log.info("[WebSocket连接][关闭] SessionID: {}, CloseStatus: {}", sessionId, status);
        
        // 移除会话
        sessionManager.removeSession(sessionId);
    }

    /**
     * 是否支持部分消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
