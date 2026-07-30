package org.springblade.core.dinger.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.dinger.config.DingerProperties;
import org.springblade.core.dinger.model.DingerMessage;
import org.springblade.core.dinger.model.DingerResult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉客户端
 *
 * @author Chill
 */
@Slf4j
@Component
public class DingerClient {

    private static final String ACCESS_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";
    private static final String SEND_MESSAGE_URL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";
    private static final String ROBOT_SEND_URL = "https://oapi.dingtalk.com/robot/send";

    private final DingerProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private String accessToken;
    private long tokenExpireTime;

    public DingerClient(DingerProperties properties) {
        this.properties = properties;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 获取访问令牌
     *
     * @return 访问令牌
     */
    public String getAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < tokenExpireTime) {
            return accessToken;
        }

        try {
            String url = String.format("%s?appkey=%s&appsecret=%s",
                ACCESS_TOKEN_URL, properties.getAppKey(), properties.getAppSecret());

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if (jsonNode.get("errcode").asInt() == 0) {
                accessToken = jsonNode.get("access_token").asText();
                tokenExpireTime = System.currentTimeMillis() + jsonNode.get("expires_in").asLong() * 1000 - 60000;
                log.info("获取钉钉访问令牌成功");
                return accessToken;
            } else {
                log.error("获取钉钉访问令牌失败: {}", jsonNode.get("errmsg").asText());
                throw new RuntimeException("获取钉钉访问令牌失败: " + jsonNode.get("errmsg").asText());
            }
        } catch (Exception e) {
            log.error("获取钉钉访问令牌异常", e);
            throw new RuntimeException("获取钉钉访问令牌异常", e);
        }
    }

    /**
     * 发送工作通知消息
     *
     * @param userIdList 用户ID列表（逗号分隔）
     * @param message    消息内容
     * @return 发送结果
     */
    public DingerResult sendMessage(String userIdList, DingerMessage message) {
        try {
            String token = getAccessToken();
            String url = String.format("%s?access_token=%s", SEND_MESSAGE_URL, token);

            Map<String, Object> params = new HashMap<>();
            params.put("agent_id", properties.getAgentId());
            params.put("userid_list", userIdList);
            params.put("msg", message.toMap());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(params), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            DingerResult result = new DingerResult();
            result.setErrcode(jsonNode.get("errcode").asInt());
            result.setErrmsg(jsonNode.get("errmsg").asText());

            if (result.getErrcode() == 0) {
                result.setTaskId(jsonNode.get("task_id").asLong());
                log.info("发送钉钉工作通知成功，taskId: {}", result.getTaskId());
            } else {
                log.error("发送钉钉工作通知失败: {}", result.getErrmsg());
            }

            return result;
        } catch (Exception e) {
            log.error("发送钉钉工作通知异常", e);
            throw new RuntimeException("发送钉钉工作通知异常", e);
        }
    }

    /**
     * 发送机器人消息
     *
     * @param message 消息内容
     * @return 发送结果
     */
    public DingerResult sendRobotMessage(DingerMessage message) {
        try {
            String url = properties.getWebhookUrl();
            if (properties.getRobotSecret() != null && !properties.getRobotSecret().isEmpty()) {
                long timestamp = System.currentTimeMillis();
                String sign = generateSign(timestamp, properties.getRobotSecret());
                url = String.format("%s&timestamp=%d&sign=%s", url, timestamp, sign);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(message.toMap()), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            DingerResult result = new DingerResult();
            result.setErrcode(jsonNode.get("errcode").asInt());
            result.setErrmsg(jsonNode.get("errmsg").asText());

            if (result.getErrcode() == 0) {
                log.info("发送钉钉机器人消息成功");
            } else {
                log.error("发送钉钉机器人消息失败: {}", result.getErrmsg());
            }

            return result;
        } catch (Exception e) {
            log.error("发送钉钉机器人消息异常", e);
            throw new RuntimeException("发送钉钉机器人消息异常", e);
        }
    }

    /**
     * 生成签名
     */
    private String generateSign(long timestamp, String secret) throws Exception {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signData);
    }

}
