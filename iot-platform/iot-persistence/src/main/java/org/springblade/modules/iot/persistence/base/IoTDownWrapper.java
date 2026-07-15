

package org.springblade.modules.iot.persistence.base;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.core.message.DownRequest;
import org.springblade.modules.iot.pojo.entity.IoTDevice;
import org.springblade.modules.iot.pojo.entity.IoTProduct;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/8/10
 */
public interface IoTDownWrapper {

  /**
   * 用于全局调用的处理
   *
   * @param product
   * @param downRequest
   * @return
   */
  default R beforeDownAction(IoTProduct product, Object data, DownRequest downRequest) {
    return null;
  }

  /**
   * 用于全局调用的处理
   *
   * @param product
   * @param ioTDevice
   * @param downRequest
   * @return
   */
  default R beforeFunctionOrConfigDown(
      IoTProduct product, IoTDevice ioTDevice, DownRequest downRequest) {
    return null;
  }
}
