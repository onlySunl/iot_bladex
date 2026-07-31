package org.springblade.core.chatgpt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天请求
 *
 * @author Chill
 */
@Data
public class ChatRequest {

    /**
     * 模型
     */
    private String model;

    /**
     * 消息列表
     */
    private List<ChatMessage> messages = new ArrayList<>();

    /**
     * 最大Token数
     */
    private Integer maxTokens;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 添加消息
     */
    public ChatRequest addMessage(ChatMessage message) {
        this.messages.add(message);
        return this;
    }

    /**
     * 创建请求
     */
    public static ChatRequest create() {
        return new ChatRequest();
    }

}
