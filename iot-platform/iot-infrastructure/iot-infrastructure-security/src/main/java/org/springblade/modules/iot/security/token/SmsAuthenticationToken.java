

package org.springblade.modules.iot.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class SmsAuthenticationToken extends AbstractAuthenticationToken {

  private final String phone;
  private final String code;

  public SmsAuthenticationToken(String phone, String code) {
    super(null);
    this.phone = phone;
    this.code = code;
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return code;
  }

  @Override
  public Object getPrincipal() {
    return phone;
  }

  public String getPhone() {
    return phone;
  }

  public String getCode() {
    return code;
  }
}
