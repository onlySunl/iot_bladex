package org.springblade.common.token;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.tool.utils.StringPool;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 内部调用 Token 提供者
 * <p>
 * 通过 HTTP 调用网关 OAuth2 Token 端点完成模拟登录，
 * 生成与正常登录完全一致的 JWT Token（含 Redis 状态记录）。
 * <p>
 * 用于 ApplicationRunner / Timer / @Async 等非 HTTP 上下文场景的 Feign 内部调用。
 *
 * @author BladeX IoT
 */
@Slf4j
@Component
public class InternalTokenProvider {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /** OAuth2 Token 端点 */
    private static final String TOKEN_URL = "http://localhost:8093/blade-auth/oauth/token";

    /** 登录参数 */
    private static final String TENANT_ID = "000000";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "cbcf24…d702";
    private static final String GRANT_TYPE = "password";
    private static final String SCOPE = "all";
    private static final String TYPE = "account";

    /** Token 缓存 */
    private volatile String cachedToken;
    private volatile long tokenExpireAt;

    /**
     * 获取系统内部调用 Token（带缓存）
     */
    public String getToken() {
        long now = System.currentTimeMillis();
        if (cachedToken != null && now < tokenExpireAt) {
            return cachedToken;
        }
        synchronized (this) {
            if (cachedToken != null && now < tokenExpireAt) {
                return cachedToken;
            }
            try {
                cachedToken = doLogin();
                log.info("[InternalToken] HTTP模拟登录成功");
                return cachedToken;
            } catch (Exception e) {
                log.error("[InternalToken] HTTP模拟登录失败", e);
                throw new RuntimeException("内部调用Token生成失败", e);
            }
        }
    }

    /**
     * HTTP POST /api/blade-auth/oauth/token 完成模拟登录
     */
    private String doLogin() {
        log.info("[InternalToken] 开始HTTP模拟登录, url={}, username={}, tenantId={}",
            TOKEN_URL, USERNAME, TENANT_ID);

        // 构建表单参数（与前端登录参数完全一致）
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tenantId", TENANT_ID);
        params.add("username", USERNAME);
        params.add("password", PASSWORD);
        params.add("grant_type", GRANT_TYPE);
        params.add("scope", SCOPE);
        params.add("type", TYPE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // 重试机制：ApplicationRunner 阶段网关可能尚未就绪
        int maxRetries = 10;
        for (int i = 0; i < maxRetries; i++) {
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, requestEntity, String.class);

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    return parseTokenResponse(response.getBody());
                }

                log.warn("[InternalToken] Token端点返回 {}，第{}次重试...", response.getStatusCode(), i + 1);
            } catch (Exception e) {
                log.warn("[InternalToken] Token端点请求异常，第{}次重试: {}", i + 1, e.getMessage());
            }

            if (i < maxRetries - 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("重试被中断");
                }
            }
        }

        throw new RuntimeException("OAuth2 Token端点请求失败，已重试" + maxRetries + "次");
    }

    /**
     * 解析 Token 响应
     */
    private String parseTokenResponse(String body) {
        try {
            JsonNode root = objectMapper.readTree(body);
            String accessToken = root.get("access_token").asText();
            long expiresIn = root.has("expires_in") ? root.get("expires_in").asLong() : 7200;

            tokenExpireAt = System.currentTimeMillis() + (expiresIn - 300) * 1000L;

            String bearerToken = TokenConstant.BEARER + StringPool.SPACE + accessToken;
            log.info("[InternalToken] HTTP模拟登录成功, expires_in={}s", expiresIn);
            return bearerToken;
        } catch (Exception e) {
            log.error("[InternalToken] 解析Token响应失败, body={}", body);
            throw new RuntimeException("解析Token响应失败", e);
        }
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        synchronized (this) {
            cachedToken = null;
            tokenExpireAt = 0;
        }
    }
}
