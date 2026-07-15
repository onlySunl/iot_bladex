

package org.springblade.modules.iot.monitor.web.controller.common;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller extends org.springblade.core.boot.ctrl.BladeController {

  /** 向后兼容的 /oauth/token 端点 转发到新的 Spring Authorization Server 的 /oauth2/token 端点 */
  @PostMapping("/oauth/token")
  public void forwardLegacyTokenEndpoint(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/oauth2/token");
    dispatcher.forward(request, response);
  }

  /** 向后兼容的 /api/oauth/token 端点 转发到新的 Spring Authorization Server 的 /oauth2/token 端点 */
  @PostMapping("/api/oauth/token")
  public void forwardApiTokenEndpoint(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/oauth2/token");
    dispatcher.forward(request, response);
  }
}
