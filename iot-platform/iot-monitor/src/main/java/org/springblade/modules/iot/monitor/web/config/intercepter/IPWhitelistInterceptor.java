

package org.springblade.modules.iot.monitor.web.config.intercepter;

import org.springblade.modules.iot.admin.system.service.IPWhitelistService;
import org.springblade.modules.iot.monitor.web.config.annotation.Whitelist;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IPWhitelistInterceptor implements HandlerInterceptor {

  @Resource private IPWhitelistService IPWhitelistService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (handler instanceof HandlerMethod handlerMethod) {
      Whitelist whitelistAnnotation = handlerMethod.getMethod().getAnnotation(Whitelist.class);
      if (whitelistAnnotation != null) {
        List<String> whitelistKeys = Arrays.asList(whitelistAnnotation.key());
        String clientIpAddress = request.getRemoteAddr();
        if (!IPWhitelistService.isWhitelisted(whitelistKeys, clientIpAddress)) {
          response.setStatus(HttpServletResponse.SC_FORBIDDEN);
          return false;
        }
      }
    }
    return true;
  }
}
