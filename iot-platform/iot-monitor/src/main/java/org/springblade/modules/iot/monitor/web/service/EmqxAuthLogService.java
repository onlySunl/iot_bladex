
package org.springblade.modules.iot.monitor.web.service;

/**
 * EMQX 认证日志服务接口 用于记录认证相关的日志信息
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/20
 */
public interface EmqxAuthLogService {

  /**
   * 记录认证结果日志
   *
   * @param username 用户名
   * @param clientId 客户端ID
   * @param ipAddress IP地址
   * @param authType 认证类型
   * @param result 认证结果
   */
  void logAuthResult(
      String username, String clientId, String ipAddress, String authType, String result);
}
