package org.springblade.core.chatgpt.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.chatgpt.config.ChatGptProperties;
import org.springblade.core.chatgpt.model.ChatMessage;
import org.springblade.core.chatgpt.model.ChatRequest;
import org.springblade.core.chatgpt.model.ChatResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ChatGPT 客户端
 *
 * @author Chill
 */
@Slf4j
@Component
public class ChatGptClient {

    private final ChatGptProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ChatGptClient(ChatGptProperties properties) {
        this.properties = properties;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 发送聊天请求
     *
     * @param request 请求
     * @return 响应
     */
    public ChatResponse chat(ChatRequest request) {
        try {
            String url = properties.getApiUrl() + "/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(properties.getApiKey());

            // 设置默认值
            if (request.getModel() == null) {
                request.setModel(properties.getModel());
            }
            if (request.getMaxTokens() == null) {
                request.setMaxTokens(properties.getMaxTokens());
            }
            if (request.getTemperature() == null) {
                request.setTemperature(properties.getTemperature());
            }

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(request), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            ChatResponse chatResponse = objectMapper.readValue(response.getBody(), ChatResponse.class);
            log.debug("ChatGPT 请求成功");
            return chatResponse;
        } catch (Exception e) {
            log.error("ChatGPT 请求失败", e);
            throw new RuntimeException("ChatGPT 请求失败: " + e.getMessage(), e);
        }
    }

    /**
     * 简单聊天
     *
     * @param message 消息
     * @return 回复内容
     */
    public String chat(String message) {
        ChatRequest request = ChatRequest.create()
            .addMessage(ChatMessage.user(message));
        ChatResponse response = chat(request);
        return response.getContent();
    }

    /**
     * 带系统提示的聊天
     *
     * @param systemPrompt 系统提示
     * @param message      用户消息
     * @return 回复内容
     */
    public String chat(String systemPrompt, String message) {
        ChatRequest request = ChatRequest.create()
            .addMessage(ChatMessage.system(systemPrompt))
            .addMessage(ChatMessage.user(message));
        ChatResponse response = chat(request);
        return response.getContent();
    }

}
