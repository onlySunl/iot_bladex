package org.springblade.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.BladeUser;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 内部调用拦截器（作为 Filter 的补充）
 * <p>
 * 识别 Feign 请求中的 X-Internal-Call 标识，构建系统用户上下文。
 * 注意：Filter 层已有 InternalCallFilter 优先处理，此 Interceptor 作为兜底。
 * <p>
 * 关键：必须使用 "_BLADE_USER_REQUEST_ATTR_" 作为 attribute key，
 * 这是 BladeX AuthUtil.getUser() 优先读取的缓存 key。
 *
 * @author system
 */
@Slf4j
public class InternalCallInterceptor implements HandlerInterceptor {

    private static final String INTERNAL_CALL_HEADER = "X-Internal-Call";
    private static final String INTERNAL_CALL_VALUE = "true";

    /**
     * BladeX AuthUtil.getUser() 优先读取的 request attribute key
     */
    private static final String BLADE_USER_REQUEST_ATTR = "_BLADE_USER_REQUEST_ATTR_";

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

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String internalCall = request.getHeader(INTERNAL_CALL_HEADER);
        if (INTERNAL_CALL_VALUE.equalsIgnoreCase(internalCall)) {
            BladeUser systemUser = createSystemUser();
            request.setAttribute(BLADE_USER_REQUEST_ATTR, systemUser);
            log.info("[内部调用] Interceptor 已设置系统用户上下文, URI: {}", request.getRequestURI());
        }
        return true;
    }

    private BladeUser createSystemUser() {
        BladeUser user = new BladeUser();
        user.setUserId(SYSTEM_USER_ID);
        user.setTenantId(SYSTEM_TENANT_ID);
        user.setAccount(SYSTEM_ACCOUNT);
        user.setUserName(SYSTEM_USER_NAME);
        user.setNickName(SYSTEM_NICK_NAME);
        user.setRoleName(SYSTEM_ROLE_NAME);
        user.setRoleId(SYSTEM_ROLE_ID);
        user.setDeptId(SYSTEM_DEPT_ID);
        user.setPostId(SYSTEM_POST_ID);
        user.setClientId(SYSTEM_CLIENT_ID);
        return user;
    }
}
