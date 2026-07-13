

package org.springblade.modules.iot.protocol.http.processor;

import org.springblade.modules.iot.dm.device.service.plugin.BaseMessageProcessor;
import org.springblade.modules.iot.pojo.protocol.http.HttpDownRequest;

/**
 * HTTP下行消息处理器接口
 *
 * <p>继承通用的BaseMessageProcessor，定义HTTP下行模块特有的处理方法 各HTTP下行处理器实现此接口，提供具体的处理逻辑
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/20
 */
public interface HttpDownMessageProcessor extends BaseMessageProcessor {

  /**
   * 处理HTTP下行消息
   *
   * @param request HTTP下行请求
   * @return 处理结果
   */
  Object process(HttpDownRequest request);

  /**
   * 是否支持处理该消息
   *
   * @param request HTTP下行请求
   * @return true表示支持，false表示不支持
   */
  boolean supports(HttpDownRequest request);

  /** 处理前的预检查（可选） */
  default boolean preCheck(HttpDownRequest request) {
    return true;
  }

  /** 处理后的后置操作（可选） */
  default void postProcess(HttpDownRequest request, Object result) {
    // 默认不做任何操作
  }

  /** 异常处理（可选） */
  default void onError(HttpDownRequest request, Exception e) {
    // 默认不做任何操作
  }
}
