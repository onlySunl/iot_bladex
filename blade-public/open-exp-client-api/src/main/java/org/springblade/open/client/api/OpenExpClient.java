package org.springblade.open.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springblade.open.client.config.OpenExpClientProperties;
import org.springblade.open.client.model.OpenExpRequest;
import org.springblade.open.client.model.OpenExpResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 开放平台客户端
 *
 * @author Chill
 */
@Slf4j
@Component
public class OpenExpClient {

    private final OpenExpClientProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OpenExpClient(OpenExpClientProperties properties) {
        this.properties = properties;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 发送请求
     *
     * @param request 请求
     * @param clazz   响应类型
     * @param <T>     响应数据类型
     * @return 响应
     */
    public <T> OpenExpResponse<T> execute(OpenExpRequest request, Class<T> clazz) {
        try {
            String url = properties.getBaseUrl() + request.getPath();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-App-Key", properties.getAppKey());
            headers.set("X-Timestamp", String.valueOf(System.currentTimeMillis()));
            headers.set("X-Sign", generateSign(request));
            
            // 添加自定义请求头
            request.getHeaders().forEach(headers::set);

            HttpEntity<Object> entity = new HttpEntity<>(request.getBody(), headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.valueOf(request.getMethod()),
                entity,
                String.class
            );

            OpenExpResponse<T> result = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(OpenExpResponse.class, clazz)
            );

            log.debug("开放平台请求成功: {} {}", request.getMethod(), url);
            return result;
        } catch (Exception e) {
            log.error("开放平台请求失败: {} {}", request.getMethod(), request.getPath(), e);
            return OpenExpResponse.error(500, e.getMessage());
        }
    }

    /**
     * GET 请求
     */
    public <T> OpenExpResponse<T> get(String path, Class<T> clazz) {
        return execute(OpenExpRequest.get(path), clazz);
    }

    /**
     * POST 请求
     */
    public <T> OpenExpResponse<T> post(String path, Object body, Class<T> clazz) {
        return execute(OpenExpRequest.post(path).body(body), clazz);
    }

    /**
     * 生成签名
     */
    private String generateSign(OpenExpRequest request) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String stringToSign = properties.getAppKey() + timestamp + request.getPath();
            
            Mac mac = Mac.getInstance(properties.getSignAlgorithm());
            mac.init(new SecretKeySpec(properties.getAppSecret().getBytes(StandardCharsets.UTF_8), properties.getSignAlgorithm()));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(signData);
        } catch (Exception e) {
            log.error("生成签名失败", e);
            return "";
        }
    }

}
