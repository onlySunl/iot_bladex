package org.springblade.modules.iot.monitor.web.service;

import org.springblade.modules.iot.monitor.web.dto.EmqxAclRequest;
import org.springblade.modules.iot.monitor.web.dto.EmqxAclResponse;

/**
 * EMQX ACL 授权服务接口 用于检查客户端的发布/订阅权限
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/20
 */
public interface EmqxAclService {

  /**
   * 检查客户端 ACL 权限
   *
   * @param request ACL 请求
   * @return ACL 响应
   */
  EmqxAclResponse checkAcl(EmqxAclRequest request);
}
