package org.springblade.modules.iot.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.common.feign.FeignRequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * 内部调用鉴权跳过过滤器配置
 * 识别Feign请求中的X-Internal-Call标识，自动构建系统用户上下文，跳过鉴权
 */
@Slf4j
@Configuration
public class InternalCallFilterConfiguration {

    /**
     * 注册内部调用过滤器，设置最高优先级（在BladeX鉴权拦截器之前执行）
     */
    @Bean
    public FilterRegistrationBean<InternalCallFilter> internalCallFilterRegistration() {
        FilterRegistrationBean<InternalCallFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new InternalCallFilter());
        registration.addUrlPatterns("/*");
        registration.setName("internalCallFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("[InternalCallFilter] 内部调用鉴权跳过过滤器已注册");
        return registration;
    }

    /**
     * 内部调用过滤器实现
     */
    static class InternalCallFilter extends OncePerRequestFilter {

        private static final String SYSTEM_ROLE = "administrator";
        private static final Long SYSTEM_TENANT_ID = 0L;
        private static final Long SYSTEM_USER_ID = 0L;
        private static final String SYSTEM_USER_NAME = "system";
        private static final String SYSTEM_NICK_NAME = "系统内部调用";

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String internalCall = request.getHeader(FeignRequestInterceptor.INTERNAL_CALL_HEADER);

            if (FeignRequestInterceptor.INTERNAL_CALL_VALUE.equalsIgnoreCase(internalCall)) {
                // 内部调用：构建系统用户上下文，跳过鉴权
                if (AuthUtil.getUser() == null) {
                    BladeUser systemUser = createSystemUser();
                    AuthUtil.setUser(systemUser);
                    if (log.isDebugEnabled()) {
                        log.debug("[内部调用] 已设置系统用户上下文, URI: {}", request.getRequestURI());
                    }
                }
            }

            filterChain.doFilter(request, response);
        }

        private BladeUser createSystemUser() {
            BladeUser user = new BladeUser();
            user.setUserId(SYSTEM_USER_ID);
            user.setTenantId(String.valueOf(SYSTEM_TENANT_ID));
            user.setName(SYSTEM_USER_NAME);
            user.setNickName(SYSTEM_NICK_NAME);
            user.setRoleName(Collections.singletonList(SYSTEM_ROLE));
            return user;
        }
    }
}
