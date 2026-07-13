

package org.springblade.modules.iot.monitor.web.config.intercepter;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 注册拦截器 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

  @Resource private IPWhitelistInterceptor IPWhitelistInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthContextIntercepter()).addPathPatterns("/api/**", "/admin/**");
    registry.addInterceptor(new APILogPrintIntercepter()).addPathPatterns("/api/**");
    registry
        .addInterceptor(IPWhitelistInterceptor)
        .addPathPatterns("/iot/**", "/api/**", "/debug/**", "/test/**", "/emqx/**", "/ct/aiot/**");
  }
}
