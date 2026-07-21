package org.springblade.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.secure.TokenInfo;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign请求拦截器 - 转发请求头和内部调用标识
 * <p>
 * 有 web 上下文时：转发原始请求的 Blade-Auth / Tenant-Id 等安全头
 * 无 web 上下文时：使用 BladeX 内置 SecureUtil.createToken() 生成系统内部调用 Token，
 * 确保 Feign 请求通过下游服务的 TokenInterceptor 校验
 */
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 内部调用标识Header
     */
    public static final String INTERNAL_CALL_HEADER = "X-Internal-Call";
    public static final String INTERNAL_CALL_VALUE = "true";

    /**
     * 系统内部调用用户信息（需满足 AuthUtil.userIncomplete() 校验）
     */
    private static final Long SYSTEM_USER_ID = 0L;
    private static final String SYSTEM_TENANT_ID = "000000";
    private static final String SYSTEM_ACCOUNT = "admin";
    private static final String SYSTEM_USER_NAME = "admin";
    private static final String SYSTEM_NICK_NAME = "系统内部调用";
    private static final String SYSTEM_ROLE_NAME = "administrator";
    private static final String SYSTEM_ROLE_ID = "1";
    private static final String SYSTEM_DEPT_ID = "0";
    private static final String SYSTEM_POST_ID = "0";
    private static final String SYSTEM_CLIENT_ID = "blade";

    /**
     * 系统 Token 缓存（避免每次调用都重新生成 JWT）
     */
    private volatile String cachedSystemToken;
    private volatile long tokenExpireAt;

    @Override
    public void apply(RequestTemplate template) {
        // 始终添加内部调用标识
        template.header(INTERNAL_CALL_HEADER, INTERNAL_CALL_VALUE);

        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            // 无 web 上下文：使用 BladeX 内置 SecureUtil 生成系统 Token
            applySystemToken(template);
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();

        // 转发原始请求的所有安全相关Header
        forwardHeader(template, request, TokenConstant.AUTH_HEADER);
        forwardHeader(template, request, "Tenant-Id");
        forwardHeader(template, request, TokenConstant.SECURE_HEADER);
        forwardHeader(template, request, "Blade-User-Type");
        forwardHeader(template, request, "Blade-Lang");
        forwardHeader(template, request, "X-Blade-Trace");
        forwardHeader(template, request, "X-Blade-Span");
    }

    /**
     * 无 web 上下文时获取 Token
     * <p>
     * 优先使用 InternalTokenProvider（模拟登录，走完整 OAuth2 流程），
     * 不可用时 fallback 到 SecureUtil.createToken()（自签 JWT）。
     */
    private void applySystemToken(RequestTemplate template) {
        String token;
        try {
            // 尝试通过 InternalTokenProvider 获取模拟登录 Token
            Object provider = SpringUtil.getBean("internalTokenProvider");
            if (provider != null) {
                // 反射调用 getToken() 避免 blade-common 直接依赖 blade-server
                token = (String) provider.getClass().getMethod("getToken").invoke(provider);
                log.info("[Feign] 使用 InternalTokenProvider 模拟登录Token");
            } else {
                token = getOrCreateSystemToken();
            }
        } catch (Exception e) {
            log.warn("[Feign] InternalTokenProvider 不可用，fallback到SecureUtil: {}", e.toString());
            token = getOrCreateSystemToken();
        }
        template.header(TokenConstant.AUTH_HEADER, token);
        template.header(TokenConstant.SECURE_HEADER, TokenConstant.SECURE_HEADER_VALUE);
    }

    /**
     * 获取或创建系统 Token（带缓存，避免频繁 JWT 签名）
     */
    private String getOrCreateSystemToken() {
        long now = System.currentTimeMillis();
        if (cachedSystemToken != null && now < tokenExpireAt) {
            return cachedSystemToken;
        }
        synchronized (this) {
            if (cachedSystemToken != null && now < tokenExpireAt) {
                return cachedSystemToken;
            }
            // 使用 BladeX 内置 SecureUtil 生成 JWT Token
            Kv kv = Kv.create()
                .set(TokenConstant.USER_ID, SYSTEM_USER_ID)
                .set(TokenConstant.ACCOUNT, SYSTEM_ACCOUNT)
                .set(TokenConstant.USER_NAME, SYSTEM_USER_NAME)
                .set(TokenConstant.NICK_NAME, SYSTEM_NICK_NAME)
                .set(TokenConstant.TENANT_ID, SYSTEM_TENANT_ID)
                .set(TokenConstant.CLIENT_ID, SYSTEM_CLIENT_ID)
                .set(TokenConstant.ROLE_NAME, SYSTEM_ROLE_NAME)
                .set(TokenConstant.ROLE_ID, SYSTEM_ROLE_ID)
                .set(TokenConstant.DEPT_ID, SYSTEM_DEPT_ID)
                .set(TokenConstant.POST_ID, SYSTEM_POST_ID);

            TokenInfo tokenInfo = SecureUtil.createToken(kv);
            cachedSystemToken = TokenConstant.BEARER + StringPool.SPACE + tokenInfo.getToken();
            // 提前 5 分钟刷新，避免边界过期
            tokenExpireAt = now + (tokenInfo.getExpire() - 300) * 1000L;
            log.info("[Feign] 已生成系统内部调用Token，过期时间: {}s", tokenInfo.getExpire());
            return cachedSystemToken;
        }
    }

    /**
     * 转发Header，空值跳过
     */
    private void forwardHeader(RequestTemplate template, HttpServletRequest request, String headerKey) {
        String value = request.getHeader(headerKey);
        if (StringUtil.isNotBlank(value)) {
            template.header(headerKey, value);
        }
    }
}
