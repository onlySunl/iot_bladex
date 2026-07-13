

package org.springblade.modules.iot.dm.device.service.push;

import org.springblade.modules.iot.pojo.framework.entity.IoTPushResult;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;

/**
 * 推送策略接口
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/9
 */
public interface PushStrategy {

  /**
   * 执行推送
   *
   * @param request 上行请求
   * @param messageJson 消息JSON字符串
   */
  IoTPushResult execute(BaseUPRequest request, String messageJson);

  /**
   * 是否支持该推送类型
   *
   * @return 是否支持
   */
  boolean isSupported();
}
