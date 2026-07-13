

package org.springblade.modules.iot.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 协议更新事件
 *
 * <p>当协议配置更新时触发，用于通知相关组件清理缓存
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/01/20
 */
@Getter
public class ProtocolUpdatedEvent extends ApplicationEvent {

  private final String productKey;
  private final String protocolType;

  /**
   * 构造函数
   *
   * @param source 事件源
   * @param productKey 产品Key
   * @param protocolType 协议类型
   */
  public ProtocolUpdatedEvent(Object source, String productKey, String protocolType) {
    super(source);
    this.productKey = productKey;
    this.protocolType = protocolType;
  }
}
