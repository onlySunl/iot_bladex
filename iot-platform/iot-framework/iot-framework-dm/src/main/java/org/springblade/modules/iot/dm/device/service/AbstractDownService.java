

package org.springblade.modules.iot.dm.device.service;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.core.service.ICodecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 下发抽象工具类
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 10:50
 */
@Slf4j
public abstract class AbstractDownService<T> extends AbstratIoTService {

  @Autowired protected ICodecService iCodecService;

  /**
   * 消息转换和编解码
   *
   * @param request
   * @return
   */
  protected abstract T convert(String request);

  protected String encodeWithShadow(String productKey, String deviceId, String payload) {
    JSONObject jsonObject = getProductConfiguration(productKey);
    JSONObject context = null;
    if (jsonObject != null) {
      // 上行报文是否需要附加影子
      Boolean requireDownShadow = jsonObject.getBool("requireDownShadow", false);
      if (requireDownShadow) {
        context = iotDeviceShadowService.getDeviceShadowObj(productKey, deviceId);
      }
    }
    // 使用新的统一编解码服务
    return iCodecService.encode(productKey, payload, context);
  }
}
