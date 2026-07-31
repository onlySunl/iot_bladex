package org.springblade.core.chatgpt.model;

import lombok.Data;

import java.util.List;

/**
 * 聊天响应
 *
 * @author Chill
 */
@Data
public class ChatResponse {

    /**
     * ID
     */
    private String id;

    /**
     * 对象
     */
    private String object;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 模型
     */
    private String model;

    /**
     * 选择列表
     */
    private List<Choice> choices;

    /**
     * 使用情况
     */
    private Usage usage;

    /**
     * 获取回复内容
     */
    public String getContent() {
        if (choices != null && !choices.isEmpty()) {
            return choices.get(0).getMessage().getContent();
        }
        return null;
    }

    @Data
    public static class Choice {
        private Integer index;
        private ChatMessage message;
        private String finishReason;
    }

    @Data
    public static class Usage {
        private Integer promptTokens;
        private Integer completionTokens;
        private Integer totalTokens;
    }

}
