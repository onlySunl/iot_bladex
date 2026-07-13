
package org.springblade.modules.iot.monitor.web.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * EMQX 请求头校验服务接口 用于验证 EMQX 请求的合法性
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/20
 */
public interface EmqxHeaderValidationService {

  /**
   * 验证请求头
   *
   * @param request HTTP请求
   * @return 验证结果
   */
  boolean validateHeader(HttpServletRequest request);

  /**
   * 获取配置的请求头键名
   *
   * @return 请求头键名
   */
  String getHeaderKey();

  /**
   * 获取配置的请求头值
   *
   * @return 请求头值
   */
  String getHeaderValue();
}
