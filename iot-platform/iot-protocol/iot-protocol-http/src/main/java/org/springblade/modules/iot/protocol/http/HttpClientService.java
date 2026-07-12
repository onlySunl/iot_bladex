package org.springblade.modules.iot.protocol.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP 客户端服务
 * <p>用于设备通过 HTTP 协议上报数据和接收指令</p>
 */
@Slf4j
@Service
public class HttpClientService {

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        this.restTemplate = new RestTemplate(factory);
    }

    /**
     * 发送 GET 请求
     */
    public String get(String url, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = buildHeaders(headers);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("HTTP GET 请求失败: url={}", url, e);
            return null;
        }
    }

    /**
     * 发送 POST 请求
     */
    public String post(String url, String body, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = buildHeaders(headers);
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("HTTP POST 请求失败: url={}", url, e);
            return null;
        }
    }

    /**
     * 异步 POST 请求
     */
    public CompletableFuture<String> postAsync(String url, String body, Map<String, String> headers) {
        return CompletableFuture.supplyAsync(() -> post(url, body, headers));
    }

    private HttpHeaders buildHeaders(Map<String, String> customHeaders) {
        HttpHeaders headers = new HttpHeaders();
        if (customHeaders != null) {
            customHeaders.forEach(headers::set);
        }
        return headers;
    }
}
