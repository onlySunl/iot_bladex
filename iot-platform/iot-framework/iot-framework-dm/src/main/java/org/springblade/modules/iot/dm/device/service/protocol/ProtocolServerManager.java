

package org.springblade.modules.iot.dm.device.service.protocol;

/**
 * 协议服务器管理器接口
 *
 * <p>用于管理协议服务器的生命周期，包括启动、停止、重启等操作
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/9
 */
public interface ProtocolServerManager {

  /**
   * 获取服务器实例
   *
   * @param productKey 产品Key
   * @return 服务器实例，如果不存在则返回null
   */
  Object getServerInstance(String productKey);

  /**
   * 检查服务器是否存活
   *
   * @param serverInstance 服务器实例
   * @return 是否存活
   */
  boolean isAlive(Object serverInstance);
}
