package org.springblade.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.BladeUser;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 内部调用拦截器
 * 参照BladeX AuthInterceptor模式实现
 * 识别Feign请求中的X-Internal-Call标识，构建系统用户上下文，跳过鉴权
 *
 * @author system
 */
@Slf4j
public class InternalCallInterceptor implements HandlerInterceptor {

    private static final String INTERNAL_CALL_HEADER = "X-Internal-Call";
    private static final String INTERNAL_CALL_VALUE = "true";

    /**
     * 系统用户信息
     */
    private static final Long SYSTEM_USER_ID = 0L;
    private static final String SYSTEM_TENANT_ID = "000000";
    private static final String SYSTEM_USER_NAME = "system";
    private static final String SYSTEM_NICK_NAME = "系统内部调用";
    private static final String SYSTEM_ROLE_NAME = "administrator";
    private static final String SYSTEM_DEPT_ID = "0";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String internalCall = request.getHeader(INTERNAL_CALL_HEADER);
        if (INTERNAL_CALL_VALUE.equalsIgnoreCase(internalCall)) {
            // 内部调用：构建系统用户上下文，设置到request属性中
            BladeUser systemUser = createSystemUser();
            // BladeX的AuthUtil从request属性中获取用户信息
            request.setAttribute("blade-user", systemUser);
            if (log.isDebugEnabled()) {
                log.debug("[内部调用] 已设置系统用户上下文, URI: {}", request.getRequestURI());
            }
        }
        return true;
    }

    /**
     * 创建系统用户
     * 参照BladeUser结构构建
     */
    private BladeUser createSystemUser() {
        BladeUser user = new BladeUser();
        user.setUserId(SYSTEM_USER_ID);
        user.setTenantId(SYSTEM_TENANT_ID);
        user.setNickName(SYSTEM_USER_NAME);
        user.setNickName(SYSTEM_NICK_NAME);
        user.setRoleName(SYSTEM_ROLE_NAME);
        user.setDeptId(SYSTEM_DEPT_ID);
        return user;
    }
}
