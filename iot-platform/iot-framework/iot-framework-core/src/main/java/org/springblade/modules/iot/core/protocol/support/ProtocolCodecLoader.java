

package org.springblade.modules.iot.core.protocol.support;

import org.springblade.modules.iot.common.exception.CodecException;

/**
 * 协议编解码支持
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 19:06
 */
public interface ProtocolCodecLoader {

  /**
   * 协议全路径包名
   *
   * @return
   */
  String getProviderType();

  /**
   * 根据全路径包名加载jar包插件
   *
   * @param definition
   * @return
   */
  default void load(ProtocolSupportDefinition definition) throws CodecException {}
}
