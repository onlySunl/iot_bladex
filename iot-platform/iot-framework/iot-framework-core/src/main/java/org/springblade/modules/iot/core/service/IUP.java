

package org.springblade.modules.iot.core.service;

import org.springblade.modules.iot.core.message.UPRequest;
import java.util.List;

/** 上行，进行业务处理和转发 */
public interface IUP {

  /** 返回服务名称 */
  String name();

  default void asyncUP(String request) {}

  default void asyncUP(UPRequest request) {}

  /** 上行处理 */
  default Object debugUP(String upMsg) {
    return null;
  }

  /** 上行处理 */
  default Object up(String upMsg) {
    return null;
  }

  /** */
  default List up(UPRequest request) {
    return null;
  }

  /** 模拟上行异步 */
  default void debugAsyncUP(String debugMsg) {}

  /**
   * 重新发送云端指令
   *
   * @param productKey 产品key
   * @param deviceId 设备序列号
   */
  default void resendStoreCommand(String productKey, String deviceId) {}
}
