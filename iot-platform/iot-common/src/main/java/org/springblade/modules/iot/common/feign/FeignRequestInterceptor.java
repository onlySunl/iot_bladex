package org.springblade.modules.iot.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign请求拦截器 - 转发请求头和内部调用标识
 * Feign属于内部调用，添加X-Internal-Call标识跳过鉴权
 */
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 内部调用标识Header
     */
    public static final String INTERNAL_CALL_HEADER = "X-Internal-Call";
    public static final String INTERNAL_CALL_VALUE = "true";

    @Override
    public void apply(RequestTemplate template) {
        // 始终添加内部调用标识
        template.header(INTERNAL_CALL_HEADER, INTERNAL_CALL_VALUE);

        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            // 无web上下文（异步回调、定时任务等场景），仅发送内部调用标识
            log.debug("[Feign] 无web上下文，仅发送内部调用标识");
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();

        // 转发原始请求的所有安全相关Header
        forwardHeader(template, request, "Blade-Auth");
        forwardHeader(template, request, "Tenant-Id");
        forwardHeader(template, request, "Blade-Requested-With");
        forwardHeader(template, request, "Blade-User-Type");
        forwardHeader(template, request, "Blade-Lang");
        forwardHeader(template, request, "X-Blade-Trace");
        forwardHeader(template, request, "X-Blade-Span");
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
