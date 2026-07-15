

package org.springblade.modules.iot.persistence.base;

import org.springblade.modules.iot.core.message.DownRequest;

/** 根据产品自定义扩展实现 */
public interface IoTDeviceExtendDTO {

  String productKey();

  /**
   * 下行扩展
   *
   * @param downRequest
   */
  default void downExt(DownRequest downRequest) {}

  /**
   * 上行扩展
   *
   * @param downRequest
   */
  default void upExt(BaseUPRequest downRequest) {}
}
