package org.springblade.core.chatgpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聊天消息
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    /**
     * 角色：system/user/assistant
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 创建系统消息
     */
    public static ChatMessage system(String content) {
        return new ChatMessage("system", content);
    }

    /**
     * 创建用户消息
     */
    public static ChatMessage user(String content) {
        return new ChatMessage("user", content);
    }

    /**
     * 创建助手消息
     */
    public static ChatMessage assistant(String content) {
        return new ChatMessage("assistant", content);
    }

}
