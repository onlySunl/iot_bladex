

package org.springblade.modules.iot.persistence.base;

import org.springblade.modules.iot.pojo.entity.IoTProduct;

/** 产品（设备）全Action生命周期接口 */
public interface IoTProductAction extends IoTAction {

  /**
   * 产品创建
   *
   * @param productKey 产品唯一标识
   * @param unionId 创建用户
   */
  void create(String productKey, String unionId);

  /**
   * 设备上线
   *
   * @param product 产品对象
   */
  void update(IoTProduct product);

  /**
   * 产品删除
   *
   * @param productKey
   */
  void delete(String productKey);

  /**
   * 产品启用
   *
   * @param productKey
   */
  void enable(String productKey);

  /**
   * 产品禁用
   *
   * @param productKey
   */
  void disable(String productKey);

  /**
   * 产品设置为公共产品
   *
   * @param productKey
   */
  void publish(String productKey);

  /**
   * 物模型创建
   *
   * @param productKey
   */
  default void metadataCreate(String productKey) {}

  /**
   * 物模型更新
   *
   * @param productKey
   */
  default void metadataUpdate(String productKey) {}

  /**
   * 物模型删除
   *
   * @param productKey
   */
  default void metadataDelete(String productKey) {}
}
