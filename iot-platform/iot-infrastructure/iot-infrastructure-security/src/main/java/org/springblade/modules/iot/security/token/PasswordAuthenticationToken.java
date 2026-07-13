

package org.springblade.modules.iot.security.token;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

public class PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

  private final String username;
  private final String password;

  public PasswordAuthenticationToken(
      Authentication clientPrincipal, Map<String, Object> additionalParameters) {
    super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
    this.username = (String) additionalParameters.get("username");
    this.password = (String) additionalParameters.get("password");
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
