package org.springblade.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign请求拦截器 - BladeX 4.9.0 超级令牌模式
 * <p>
 * 有 web 上下文时：转发原始请求的安全头（Blade-Auth、Tenant-Id 等）
 * 无 web 上下文时：使用超级令牌（Super Token）模式发起内部调用
 * <p>
 * 超级令牌请求头格式：
 *   Blade-Auth: ak-{super-token}
 *   Blade-Requested-With: BladeHttpRequest
 */
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final String SYSTEM_TENANT_ID = "000000";

    private static final String AUTH_HEADER = "Blade-Auth";
    private static final String REQUESTED_WITH_HEADER = "Blade-Requested-With";
    private static final String REQUESTED_WITH_VALUE = "BladeHttpRequest";

    /**
     * 超级令牌，配置项：blade.token.super-token
     */
    @Value("${blade.token.super-token:}")
    private String superToken;

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
            // 有 web 上下文：转发原始请求的安全头
            forwardHeader(template, request, TokenConstant.AUTH_HEADER);
            forwardHeader(template, request, "Tenant-Id");
            forwardHeader(template, request, TokenConstant.SECURE_HEADER);
            forwardHeader(template, request, "Blade-User-Type");
            forwardHeader(template, request, "Blade-Lang");
            forwardHeader(template, request, "X-Blade-Trace");
            forwardHeader(template, request, "X-Blade-Span");
        } else {
            // 无 web 上下文：使用超级令牌模式
            template.header(AUTH_HEADER, "ak-" + superToken);
            template.header(REQUESTED_WITH_HEADER, REQUESTED_WITH_VALUE);
            template.header("Tenant-Id", SYSTEM_TENANT_ID);
        }
        log.debug("[Feign] 请求: {} {}, Headers: {}", template.method(), template.url(), template.headers());
    }

    private void forwardHeader(RequestTemplate template, HttpServletRequest request, String headerKey) {
        String value = request.getHeader(headerKey);
        if (StringUtil.isNotBlank(value)) {
            template.header(headerKey, value);
        }
    }
}
