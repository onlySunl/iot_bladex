

package org.springblade.modules.iot.security.converter;

import org.springblade.modules.iot.security.token.OAuth2CaptchaAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class OAuth2CaptchaAuthenticationConverter implements AuthenticationConverter {

  @Override
  public Authentication convert(HttpServletRequest request) {
    String grantType = request.getParameter("grant_type");
    if (!"captcha".equals(grantType)) {
      return null;
    }

    // 从 SecurityContext 获取客户端认证信息
    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
    if (!(clientPrincipal instanceof OAuth2ClientAuthenticationToken)) {
      return null;
    }

    // 提取请求参数
    Map<String, Object> additionalParameters = new HashMap<>();
    additionalParameters.put("username", request.getParameter("username"));
    additionalParameters.put("password", request.getParameter("password"));
    additionalParameters.put("code", request.getParameter("code"));
    additionalParameters.put("uuid", request.getParameter("uuid"));
    additionalParameters.put("grant_type", grantType);

    return new OAuth2CaptchaAuthenticationToken(clientPrincipal, additionalParameters);
  }
}
