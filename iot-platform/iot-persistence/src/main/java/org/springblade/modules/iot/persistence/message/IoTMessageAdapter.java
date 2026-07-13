
package org.springblade.modules.iot.persistence.message;

import org.springblade.modules.iot.persistence.base.BaseDownRequest;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;

/**
 * 消息适配器名称，用于有特定需求的情况
 *
 * <p>用处不大
 */
public interface IoTMessageAdapter {

  /**
   * @return 适配器名称
   */
  String name();

  /** 格式化上行消息 */
  String formatUpMessage(BaseUPRequest upRequest);

  /** 格式化下行消息 */
  String formatDownMessage(BaseDownRequest downRequest);
}
