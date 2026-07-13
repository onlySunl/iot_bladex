

package org.springblade.modules.iot.protocol.http.service;

import org.springblade.modules.iot.common.protocol.support.ProtocolCodecSupport.CodecMethod;

/**
 * HTTP编解码服务接口
 *
 * <p>定义HTTP协议特有的编解码方法
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/01/20
 */
public interface IHTTPCodecService {

  /**
   * HTTP编解码 - 支持统一的CodecMethod
   *
   * @param productKey 产品Key
   * @param payload 原始数据
   * @param codecMethod 编解码方法
   * @return 编解码结果
   */
  String httpCodec(String productKey, String payload, CodecMethod codecMethod);

  /**
   * 检查是否支持HTTP编解码
   *
   * @param productKey 产品Key
   * @return 是否支持
   */
  boolean support(String productKey);
}
