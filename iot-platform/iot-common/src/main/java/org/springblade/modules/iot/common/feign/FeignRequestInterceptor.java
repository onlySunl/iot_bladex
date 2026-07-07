package org.springblade.modules.iot.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            log.warn("attr为空，无web上下文");
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();

        String bladeAuth = request.getHeader("Blade-Auth");

        // 核心鉴权Token
        setHeader(template, request, "Blade-Auth");
        // 租户ID
        setHeader(template, request, "Tenant-Id");
        // Blade安全签名必需头部（缺失会报请求头不完整）
        setHeader(template, request, "Blade-Requested-With");
        setHeader(template, request, "Blade-User-Type");
        setHeader(template, request, "Blade-Lang");
        // 链路追踪
        setHeader(template, request, "X-Blade-Trace");
        setHeader(template, request, "X-Blade-Span");
    }

    /** 封装设置Header，空值跳过 */
    private void setHeader(RequestTemplate template, HttpServletRequest request, String headerKey) {
        String value = request.getHeader(headerKey);
        if (value != null) {
            template.header(headerKey, value);
        }
    }
}